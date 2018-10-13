package com.battaglino.santiago.ubykuo.ui.main.mvvm.view

import com.battaglino.santiago.ubykuo.base.mvvm.view.BaseView
import com.battaglino.santiago.ubykuo.db.entity.Repo
import com.battaglino.santiago.ubykuo.ui.main.activity.MainDetailActivity
import com.battaglino.santiago.ubykuo.ui.main.mvvm.viewmodel.MainDetailViewModel
import kotlinx.android.synthetic.main.activity_main_detail.*
import kotlinx.android.synthetic.main.content_main_detail.*

/**
 * Created by Santiago Battaglino.
 */
class MainDetailView(activity: MainDetailActivity, viewModel: MainDetailViewModel) :
        BaseView<MainDetailActivity, MainDetailViewModel>(activity, viewModel) {

    private var mRepo: Repo? = null

    private val toolbar = baseActivity.get()?.toolbar
    private val name = baseActivity.get()?.name
    private val fullName = baseActivity.get()?.fullName
    private val score = baseActivity.get()?.score
    private val forks = baseActivity.get()?.forks
    private val description = baseActivity.get()?.description

    init {
        mRepo = baseActivity.get()?.getRepo()
        setUpNavigation()
        setValues()
    }

    private fun setUpNavigation() {
        baseActivity.get()?.setSupportActionBar(toolbar)
        baseActivity.get()?.supportActionBar?.setHomeButtonEnabled(true)
        baseActivity.get()?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun subscribeUiToLiveData() {

    }

    private fun setValues() {
        name?.text = mRepo?.name
        fullName?.text = mRepo?.name
        score?.rating = getScore(mRepo?.score)
        forks?.text = mRepo?.forksCount.toString()
        description?.text = mRepo?.description
    }

    private fun getScore(score: Double?): Float {
        return if (score != null) {
            (score * 5 / 100).toFloat()
        } else {
            0f
        }
    }
}