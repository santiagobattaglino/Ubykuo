package com.battaglino.santiago.ubykuo.ui.main.mvvm.view

import android.arch.lifecycle.Observer
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
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

    private var mRepos: List<Repo>? = null
    private var mAdapter: RepoAdapter? = null
    private var mQueryString: String = "kotlin"

    init {
        setUpNavigation(baseActivity.get()?.toolbar)
        setUpSearchView()
        setUpTitle()
        setUpRecyclerView()
    }

    override fun subscribeUiToLiveData() {
        baseViewModel.repos.observe(baseActivity.get()!!, Observer<List<Repo>> { repos ->
            doRepos(repos)
        })
    }

    private fun doRepos(repos: List<Repo>?) {
        if (repos == null || repos.isEmpty()) {
            baseViewModel.fetchReposFromServer(mQueryString, null, null, null, null, false)
        } else {
            mRepos = repos
            mAdapter?.mRepos = repos
            mAdapter?.notifyDataSetChanged()
            baseActivity.get()?.searchView?.setSuggestions(getSuggestions(repos))
        }
    }

    override fun showDataInUi() {

    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            baseActivity.get()?.searchView?.setSuggestions(getSuggestions(mRepos))
        }
        return false
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            baseViewModel.fetchReposFromServer(query, null, null, null, null, false)
            mQueryString = query
            setUpTitle()
        }
        return false
    }

    override fun onSearchViewShown() {

    }

    override fun onSearchViewClosed() {

    }

    override fun viewHolderClick(view: View, position: Int, item: Repo?) {

    }

    private fun setUpNavigation(toolbar: Toolbar?) {
        baseActivity.get()?.setSupportActionBar(toolbar)
    }

    private fun setUpSearchView() {
        baseActivity.get()?.searchView?.setEllipsize(true)
        baseActivity.get()?.searchView?.setOnQueryTextListener(this)
    }

    private fun setUpTitle() {
        baseActivity.get()?.mainTitle?.text = String.format(Locale.getDefault(), "%s: %s",
                baseActivity.get()?.getString(R.string.mainTitle), mQueryString
        )
    }

    private fun setUpRecyclerView() {
        val layoutManager = LinearLayoutManager(baseActivity.get(), LinearLayoutManager.VERTICAL, false)
        baseActivity.get()?.recyclerviewHorizontal?.layoutManager = layoutManager

        mAdapter = RepoAdapter(baseActivity.get()!!, this, mRepos)
        baseActivity.get()?.recyclerviewHorizontal?.adapter = mAdapter
    }

    private fun getSuggestions(repos: List<Repo>?): Array<String?> {
        var suggestions = arrayOfNulls<String>(0)
        repos?.forEach {
            suggestions += it.name
        }
        return suggestions
    }
}
