package com.battaglino.santiago.ubykuo.di.modules

import com.battaglino.santiago.ubykuo.ui.main.activity.MainDetailActivity
import com.battaglino.santiago.ubykuo.ui.main.mvvm.view.MainDetailView
import com.battaglino.santiago.ubykuo.ui.main.mvvm.viewmodel.MainDetailViewModel
import dagger.Module
import dagger.Provides

/**
 * Created by Santiago Battaglino.
 */
@Module
class MainDetailActivityModule {

    @Provides
    internal fun provideMainDetailView(
            activity: MainDetailActivity,
            viewModel: MainDetailViewModel): MainDetailView {
        return MainDetailView(activity, viewModel)
    }
}