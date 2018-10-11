package com.battaglino.santiago.ubykuo.ui.main.mvvm.view

import android.arch.lifecycle.Observer
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import com.battaglino.santiago.ubykuo.base.mvvm.view.BaseView
import com.battaglino.santiago.ubykuo.db.entity.Repo
import com.battaglino.santiago.ubykuo.ui.main.activity.MainActivity
import com.battaglino.santiago.ubykuo.ui.main.adapter.RepoAdapter
import com.battaglino.santiago.ubykuo.ui.main.mvvm.viewmodel.MainViewModel
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.main_activity.*

/**
 * Created by Santiago Battaglino.
 */
class MainView(activity: MainActivity, viewModel: MainViewModel) :
        BaseView<MainActivity, MainViewModel>(activity, viewModel),
        MaterialSearchView.OnQueryTextListener, RepoAdapter.OnViewHolderClick {

    private var mRepos: List<Repo>? = null
    private var mAdapter: RepoAdapter? = null

    init {
        setUpNavigation(baseActivity.get()?.toolbar)
        setUpSearchView()
        setUpRecyclerView()
    }

    override fun subscribeUiToLiveData() {
        baseViewModel.repos.observe(baseActivity.get()!!, Observer<List<Repo>> { repos ->
            doRepos(repos)
        })
    }

    private fun doRepos(repos: List<Repo>?) {
        if (repos == null || repos.isEmpty()) {
            baseViewModel.fetchReposFromServer()
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
        return false
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onClick(view: View, position: Int, item: Repo?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setUpNavigation(toolbar: Toolbar?) {
        baseActivity.get()?.setSupportActionBar(toolbar)
    }

    private fun setUpSearchView() {
        baseActivity.get()?.searchView?.setEllipsize(true)
        baseActivity.get()?.searchView?.setOnQueryTextListener(this)
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
