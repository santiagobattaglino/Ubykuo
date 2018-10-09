package com.battaglino.santiago.ubykuo.ui.main.mvvm.viewmodel

import android.app.Application
import android.arch.lifecycle.LiveData

import com.battaglino.santiago.ubykuo.base.mvvm.viewmodel.BaseViewModel
import com.battaglino.santiago.ubykuo.db.entity.Repo
import com.battaglino.santiago.ubykuo.ui.main.repository.RepoRepository

import javax.inject.Inject

/**
 * Created by Santiago Battaglino.
 */
class MainViewModel @Inject
constructor(application: Application, repository: RepoRepository) : BaseViewModel<Repo, RepoRepository>(application) {

    val repos: LiveData<List<Repo>>
        get() = useCaseRepository!!.mDataList

    init {
        useCaseRepository = repository
    }

    fun fetchReposFromServer() {
        useCaseRepository!!.requestDataToServer()
    }
}