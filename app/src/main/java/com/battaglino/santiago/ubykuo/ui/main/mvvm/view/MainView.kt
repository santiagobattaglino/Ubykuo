package com.battaglino.santiago.ubykuo.ui.main.mvvm.view

import android.arch.lifecycle.Observer
import android.content.Intent
import android.preference.PreferenceManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.battaglino.santiago.ubykuo.R
import com.battaglino.santiago.ubykuo.base.mvvm.view.BaseView
import com.battaglino.santiago.ubykuo.db.entity.Repo
import com.battaglino.santiago.ubykuo.global.Constants
import com.battaglino.santiago.ubykuo.ui.main.activity.MainActivity
import com.battaglino.santiago.ubykuo.ui.main.activity.MainDetailActivity
import com.battaglino.santiago.ubykuo.ui.main.adapter.RepoAdapter
import com.battaglino.santiago.ubykuo.ui.main.mvvm.viewmodel.MainViewModel
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

/**
 * Created by Santiago Battaglino.
 */
class MainView(activity: MainActivity, viewModel: MainViewModel) :
        BaseView<MainActivity, MainViewModel>(activity, viewModel),
        MaterialSearchView.OnQueryTextListener, MaterialSearchView.SearchViewListener,
        RepoAdapter.OnViewHolderClick {

    private var mRepos: List<Repo> = emptyList()
    private var mAdapter: RepoAdapter = RepoAdapter(baseActivity.get(), this)
    private var mQueryString: String = getQueryString()

    private val toolbar = baseActivity.get()?.toolbar
    private val searchView = baseActivity.get()?.searchView
    private val mainTitle = baseActivity.get()?.mainTitle
    private val recyclerviewHorizontal = baseActivity.get()?.recyclerviewHorizontal
    private val layoutManager = LinearLayoutManager(baseActivity.get(), LinearLayoutManager.VERTICAL, false)

    init {
        setUpNavigation()
        setUpSearchView()
        setTitle()
        setUpRecyclerView()
    }

    private fun setUpNavigation() {
        baseActivity.get()?.setSupportActionBar(toolbar)
    }

    private fun setUpSearchView() {
        searchView?.setEllipsize(true)
        searchView?.setOnQueryTextListener(this)
        baseViewModel.getReposCached(getQueryString())
    }

    private fun setUpRecyclerView() {
        recyclerviewHorizontal?.layoutManager = layoutManager
        recyclerviewHorizontal?.adapter = mAdapter
    }

    override fun subscribeUiToLiveData() {
        subscribeSuggestions()
        subscribeReposByQuery()
    }

    private fun subscribeSuggestions() {
        baseViewModel.getSuggestions()?.observe(baseActivity.get()!!, Observer<List<Repo>> { suggestions ->
            if (suggestions != null) {
                setSuggestions(suggestions)
            }
        })
    }

    private fun subscribeReposByQuery() {
        baseViewModel.getReposByQuery()?.observe(baseActivity.get()!!, Observer<List<Repo>> { repos ->
            if (repos != null && !repos.isEmpty()) {
                mRepos = repos
                fillRepoAdapter(repos)
            } else {
                searchView?.showSearch(false)
                searchView?.visibility = View.VISIBLE
            }
        })
    }

    private fun setSuggestions(repos: List<Repo>) {
        searchView?.setSuggestions(getSuggestions(repos))
    }

    private fun getSuggestions(repos: List<Repo>): Array<String?> {
        var suggestions = arrayOfNulls<String>(0)
        repos.forEach {
            suggestions += it.name + " " + it.fullName
        }
        return suggestions
    }

    private fun fillRepoAdapter(repos: List<Repo>) {
        mAdapter.mRepos = repos
    }

    override fun onQueryTextChange(newText: String): Boolean {
        return false
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        setQueryString(query)
        doSearch()
        return false
    }

    private fun doSearch() {
        setTitle()
        baseViewModel.findReposByQueryFromServer(mQueryString, "stars", "desc", null, null, false)
    }

    private fun setTitle() {
        if (!mQueryString.isEmpty()) {
            mainTitle?.visibility = View.VISIBLE
            mainTitle?.text = String.format(Locale.getDefault(), "%s: %s",
                    baseActivity.get()?.getString(R.string.mainTitle), mQueryString
            )
        }
    }

    private fun setQueryString(query: String) {
        mQueryString = query
        val preferences = PreferenceManager.getDefaultSharedPreferences(baseActivity.get())
        preferences.edit().putString(Constants.QUERY, query).apply()
    }

    private fun getQueryString(): String {
        val preferences = PreferenceManager.getDefaultSharedPreferences(baseActivity.get())
        mQueryString = preferences.getString(Constants.QUERY, "")
        return mQueryString
    }

    override fun onSearchViewShown() {

    }

    override fun onSearchViewClosed() {

    }

    override fun repoViewClickFromList(view: View, position: Int, repo: Repo) {
        val intent = Intent(baseActivity.get(), MainDetailActivity::class.java)
        intent.putExtra(Constants.INTENT_REPO, repo)
        baseActivity.get()?.startActivity(intent)
    }
}
