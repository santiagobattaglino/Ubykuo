package com.battaglino.santiago.ubykuo.ui.main.mvvm.view

import android.support.v7.widget.Toolbar
import com.battaglino.santiago.ubykuo.base.mvvm.view.BaseView
import com.battaglino.santiago.ubykuo.ui.main.activity.MainActivity
import com.battaglino.santiago.ubykuo.ui.main.mvvm.viewmodel.MainViewModel
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.main_activity.*

/**
 * Created by Santiago Battaglino.
 */
class MainView(activity: MainActivity, viewModel: MainViewModel) :
        BaseView<MainActivity, MainViewModel>(activity, viewModel),
        MaterialSearchView.OnQueryTextListener {

    init {
        setUpNavigation(baseActivity.get()?.toolbar)
        setUpSearchView()
    }

    override fun subscribeUiToLiveData() {

    }

    override fun showDataInUi() {

    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    private fun setUpNavigation(toolbar: Toolbar?) {
        baseActivity.get()?.setSupportActionBar(toolbar)
    }

    private fun setUpSearchView() {
        baseActivity.get()?.searchView?.setEllipsize(true)
        baseActivity.get()?.searchView?.setOnQueryTextListener(this)
    }
}
