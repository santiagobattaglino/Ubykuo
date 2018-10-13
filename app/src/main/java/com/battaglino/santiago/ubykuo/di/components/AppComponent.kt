package com.battaglino.santiago.ubykuo.di.components

import com.battaglino.santiago.ubykuo.App
import com.battaglino.santiago.ubykuo.di.modules.ActivityBuilder
import com.battaglino.santiago.ubykuo.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by Santiago Battaglino.
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuilder::class])
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

}
