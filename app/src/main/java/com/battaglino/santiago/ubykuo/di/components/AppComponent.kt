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
 *
 *
 * Android apps have one application class. That is why we have one application component.
 * This component is responsible for providing application scope instances (eg. OkHttp, Database, SharedPrefs.).
 * This Component is root of our dagger graph. Application component is providing 4 module in our app.
 *
 *
 * AndroidInjectionModule : We didn’t create this. It is an internal class in Dagger.
 * Provides our activities and fragments with given module.
 *
 *
 * ActivityBuilder and FragmentBuilder: We created this module. This is a given module to dagger.
 * We map all our activities and fragments here. And Dagger know our activities and fragments in compile time.
 *
 *
 * AppModule: We provide retrofit client, okhttp, UseCaseRepository, etc here.
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
