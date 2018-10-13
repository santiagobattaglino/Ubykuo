package com.battaglino.santiago.ubykuo.ui.main.mvvm.view

import android.arch.lifecycle.Observer
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.battaglino.santiago.ubykuo.R
import com.battaglino.santiago.ubykuo.base.mvvm.view.BaseView
import com.battaglino.santiago.ubykuo.db.entity.Repo
import com.battaglino.santiago.ubykuo.ui.main.activity.MainActivity
import com.battaglino.santiago.ubykuo.ui.main.adapter.RepoAdapter
import com.battaglino.santiago.ubykuo.ui.main.mvvm.viewmodel.MainViewModel
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.main_activity.*
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
    private var mQueryString: String = ""

    private val toolbar = baseActivity.get()?.toolbar
    private val searchView = baseActivity.get()?.searchView
    private val mainTitle = baseActivity.get()?.mainTitle
    private val recyclerviewHorizontal = baseActivity.get()?.recyclerviewHorizontal
    private val layoutManager = LinearLayoutManager(baseActivity.get(), LinearLayoutManager.VERTICAL, false)

    init {
        mQueryString = "kotlin"
        setUpNavigation()
        setUpSearchView()
        setUpRecyclerView()
    }

    private fun setUpNavigation() {
        baseActivity.get()?.setSupportActionBar(toolbar)
    }

    private fun setUpSearchView() {
        searchView?.setEllipsize(true)
        searchView?.setOnQueryTextListener(this)
    }

    private fun setUpRecyclerView() {
        recyclerviewHorizontal?.layoutManager = layoutManager
        recyclerviewHorizontal?.adapter = mAdapter
    }

    private fun doSearch() {
        setTitle()
        baseViewModel.findReposByQueryFromServer(mQueryString, "stars", "desc", null, null, false)
    }

    private fun setTitle() {
        mainTitle?.text = String.format(Locale.getDefault(), "%s: %s",
                baseActivity.get()?.getString(R.string.mainTitle), mQueryString
        )
    }

    override fun subscribeUiToLiveData() {
        subscribeSuggestions()
        subscribeRepos()
        //subscribeReposByQuery()
    }

    private fun subscribeSuggestions() {
        baseViewModel.getSuggestions()?.observe(baseActivity.get()!!, Observer<List<Repo>> { suggestions ->
            if (suggestions != null) {
                setSuggestions(suggestions)
            }
        })
    }

    private fun subscribeRepos() {
        baseViewModel.getRepos()?.observe(baseActivity.get()!!, Observer<List<Repo>> { repos ->
            if (repos != null && !repos.isEmpty()) {
                mRepos = repos
                fillRepoAdapter(repos)
            } else {
                searchView?.showSearch(true)
                searchView?.visibility = View.VISIBLE
            }
        })
    }

    private fun subscribeReposByQuery() {
        baseViewModel.getReposByQuery()?.observe(baseActivity.get()!!, Observer<List<Repo>> { repos ->
            if (repos != null) {
                mRepos = repos
                fillRepoAdapter(repos)
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
        mQueryString = query
        doSearch()
        return false
    }

    override fun onSearchViewShown() {

    }

    override fun onSearchViewClosed() {

    }

    override fun viewHolderClick(view: View, position: Int, item: Repo?) {

    }
}
