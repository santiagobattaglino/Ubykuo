package com.battaglino.santiago.ubykuo.di.modules

import android.app.Application
import com.battaglino.santiago.ubykuo.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Santiago Battaglino.
 */
@Module(includes = [RetrofitModule::class])
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: App): Application {
        return application
    }
}
