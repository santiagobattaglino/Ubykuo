package com.battaglino.santiago.ubykuo.ui.main.activity

import android.os.Bundle
import com.battaglino.santiago.ubykuo.R
import com.battaglino.santiago.ubykuo.base.activity.BaseActivity
import com.battaglino.santiago.ubykuo.ui.main.fragment.MainFragment
import dagger.android.AndroidInjection

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }

    override fun injectThis() {
        AndroidInjection.inject(this)
    }
}
