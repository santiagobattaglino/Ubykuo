package com.battaglino.santiago.ubykuo.base.mvvm.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel

import com.battaglino.santiago.ubykuo.data.repository.UseCaseRepository

/**
 * Created by Santiago Battaglino.
 */
abstract class BaseViewModel<T, R : UseCaseRepository<T>>(application: Application) : AndroidViewModel(application) {

    protected var useCaseRepository: R? = null
}
