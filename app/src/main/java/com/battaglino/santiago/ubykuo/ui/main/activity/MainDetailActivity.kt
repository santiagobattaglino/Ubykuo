package com.battaglino.santiago.ubykuo.ui.main.activity

import android.os.Bundle
import com.battaglino.santiago.ubykuo.R
import com.battaglino.santiago.ubykuo.base.activity.BaseActivity
import com.battaglino.santiago.ubykuo.db.entity.Repo
import com.battaglino.santiago.ubykuo.global.Constants
import com.battaglino.santiago.ubykuo.ui.main.mvvm.view.MainDetailView
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Created by Santiago Battaglino.
 */
class MainDetailActivity : BaseActivity() {

    @set:Inject
    internal var view: MainDetailView? = null

    private var mRepo: Repo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main_detail)
        mRepo = intent.getParcelableExtra(Constants.INTENT_REPO)
        super.onCreate(savedInstanceState)
    }

    override fun injectThis() {
        AndroidInjection.inject(this)
    }

    fun getRepo(): Repo? {
        return mRepo
    }
}