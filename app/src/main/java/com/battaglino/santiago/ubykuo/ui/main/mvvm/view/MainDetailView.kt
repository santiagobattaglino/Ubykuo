package com.battaglino.santiago.ubykuo.ui.main.mvvm.view

import com.battaglino.santiago.ubykuo.R
import com.battaglino.santiago.ubykuo.base.mvvm.view.BaseView
import com.battaglino.santiago.ubykuo.db.entity.Repo
import com.battaglino.santiago.ubykuo.ui.main.activity.MainDetailActivity
import com.battaglino.santiago.ubykuo.ui.main.mvvm.viewmodel.MainDetailViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

/**
 * Created by Santiago Battaglino.
 */
class MainDetailView(activity: MainDetailActivity, viewModel: MainDetailViewModel) :
        BaseView<MainDetailActivity, MainDetailViewModel>(activity, viewModel) {

    private var mRepo: Repo? = null

    private val toolbar = baseActivity.get()?.toolbar
    private val mainTitle = baseActivity.get()?.mainTitle

    init {
        mRepo = baseActivity.get()?.getRepo()
        setUpNavigation()
        setTitle()
    }

    private fun setUpNavigation() {
        baseActivity.get()?.setSupportActionBar(toolbar)
        baseActivity.get()?.supportActionBar?.setHomeButtonEnabled(true)
        baseActivity.get()?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun subscribeUiToLiveData() {

    }

    private fun setTitle() {
        mainTitle?.text = String.format(Locale.getDefault(), "%s: %s",
                baseActivity.get()?.getString(R.string.mainTitle), mRepo?.name
        )
    }
}