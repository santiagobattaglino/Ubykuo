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

    fun getReposFromDb(): LiveData<List<Repo>>? {
        return useCaseRepository?.getDataList()
    }

    fun findReposByQueryFromServer(q: String, sort: String?, order: String?, page: String?, perPage: String?, dispose: Boolean) {
        useCaseRepository?.findReposByQueryFromServer(q, sort, order, page, perPage, dispose)
    }

    fun findReposByQueryFromDb(query: String): LiveData<List<Repo>>? {
        return useCaseRepository?.findReposByQueryFromDb(query)
    }

    fun findSuggestionsByQueryFromDb() {
        useCaseRepository?.findSuggestionsFromDb()
    }

    fun getFoundSuggestions(): LiveData<List<Repo>>? {
        return useCaseRepository?.getFoundSuggestionsDataList()
    }

    fun getFoundRepos(): LiveData<List<Repo>>? {
        return useCaseRepository?.getFoundReposDataList()
    }
}