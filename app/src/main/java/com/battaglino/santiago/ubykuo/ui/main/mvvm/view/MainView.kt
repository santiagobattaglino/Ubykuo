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
    private var mAdapter: RepoAdapter = RepoAdapter(baseActivity.get()!!, this)
    private var mQueryString: String = "kotlin"

    private val toolbar = baseActivity.get()?.toolbar
    private val searchView = baseActivity.get()?.searchView
    private val mainTitle = baseActivity.get()?.mainTitle
    private val recyclerviewHorizontal = baseActivity.get()?.recyclerviewHorizontal
    private val layoutManager = LinearLayoutManager(baseActivity.get(), LinearLayoutManager.VERTICAL, false)

    init {
        setUpNavigation()
        setUpSearchView()
        setUpTitle()
        setUpRecyclerView()
    }

    private fun setUpNavigation() {
        baseActivity.get()?.setSupportActionBar(toolbar)
    }

    private fun setUpSearchView() {
        searchView?.setEllipsize(true)
        searchView?.setOnQueryTextListener(this)
    }

    private fun setUpTitle() {
        mainTitle?.text = String.format(Locale.getDefault(), "%s: %s",
                baseActivity.get()?.getString(R.string.mainTitle), mQueryString
        )
    }

    private fun setUpRecyclerView() {
        recyclerviewHorizontal?.layoutManager = layoutManager
        recyclerviewHorizontal?.adapter = mAdapter
    }



    override fun subscribeUiToLiveData() {
        subscribeReposByQuery()
    }


    private fun subscribeReposByQuery() {
        baseViewModel.getFoundRepos()?.observe(baseActivity.get()!!, Observer<List<Repo>> { repos ->
            if (repos == null || repos.isEmpty()) {
                baseViewModel.findReposByQueryFromServer(mQueryString, "stars", "desc", null, null, false)
            } else {
                mRepos = repos
                searchView?.setSuggestions(getSuggestions(repos))
                fillAdapter(repos)
            }
        })
    }

    private fun fillAdapter(repos: List<Repo>) {
        mAdapter.mRepos = repos
    }

    private fun getSuggestions(repos: List<Repo>): Array<String?> {
        var suggestions = arrayOfNulls<String>(0)
        repos.forEach {
            suggestions += it.name
        }
        return suggestions
    }



    override fun showDataInUi() {

    }

    override fun onQueryTextChange(newText: String): Boolean {
        searchView?.setSuggestions(getSuggestions(mRepos))
        return false
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        mQueryString = query
        baseViewModel.findReposByQueryFromDb(query)
        setUpTitle()
        return false
    }

    override fun onSearchViewShown() {

    }

    override fun onSearchViewClosed() {

    }

    override fun viewHolderClick(view: View, position: Int, item: Repo?) {

    }
}
