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

    init {
        useCaseRepository = repository
    }

    fun getSuggestions(): LiveData<List<Repo>>? {
        return useCaseRepository?.getSuggestions()
    }

    fun getRepos(): LiveData<List<Repo>>? {
        return useCaseRepository?.getDataList()
    }

    fun getReposByQuery(): LiveData<List<Repo>>? {
        return useCaseRepository?.getReposByQuery()
    }

    fun findReposByQueryFromServer(q: String, sort: String?, order: String?, page: String?, perPage: String?, dispose: Boolean) {
        useCaseRepository?.findReposByQueryFromServer(q, sort, order, page, perPage, dispose)
    }
}