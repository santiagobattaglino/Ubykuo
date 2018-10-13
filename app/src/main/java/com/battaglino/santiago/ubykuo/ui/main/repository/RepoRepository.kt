package com.battaglino.santiago.ubykuo.ui.main.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.battaglino.santiago.ubykuo.data.model.ApiResponse
import com.battaglino.santiago.ubykuo.data.repository.UseCaseRepository
import com.battaglino.santiago.ubykuo.data.service.ApiService
import com.battaglino.santiago.ubykuo.db.AppDatabase
import com.battaglino.santiago.ubykuo.db.entity.Repo
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Santiago Battaglino.
 */
class RepoRepository @Inject
constructor(context: Application, private val mClient: ApiService) : UseCaseRepository<Repo>(context) {

    private var mDataBase: AppDatabase? = null
    private val mDisposable: CompositeDisposable = CompositeDisposable()

    private var mFoundRepos: MutableLiveData<List<Repo>> = MutableLiveData()
    private var mFoundSuggestions: LiveData<List<Repo>> = MutableLiveData()

    override fun initLocalData() {
        mDataBase = AppDatabase.getDatabaseBuilder(context)
        setDataList(mDataBase!!.repoModel().loadList())
    }

    override fun addData(data: Repo) {
        mDataBase!!.repoModel().insert(data)
        setData(mDataBase!!.repoModel().load(data.id))
    }

    override fun addDataList(dataList: List<Repo>) {
        mDataBase!!.repoModel().insertAll(dataList)
        setDataList(mDataBase!!.repoModel().loadList())
    }

    override fun requestDataToServer() {

    }

    fun findReposByQueryFromServer(q: String, sort: String?, order: String?, page: String?, perPage: String?, dispose: Boolean = false) {
        mClient.getRepos(q, sort, order, page, perPage)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<ApiResponse<Repo>> {
                    override fun onSubscribe(d: Disposable) {
                        if (dispose)
                            mDisposable.add(d)
                    }

                    override fun onNext(dataListFromServer: ApiResponse<Repo>) {
                        addFoundReposDataList(dataListFromServer.items, q)
                        if (dispose)
                            mDisposable.dispose()
                    }

                    override fun onError(e: Throwable) {
                        Log.e("error", e.message)
                    }

                    override fun onComplete() {

                    }
                })
    }

    fun getSuggestions(): LiveData<List<Repo>> {
        mFoundSuggestions = mDataBase!!.repoModel().loadSuggestions()
        return mFoundSuggestions
    }

    fun getReposByQuery(): LiveData<List<Repo>> {
        return mFoundRepos
    }

    private fun addFoundReposDataList(repos: List<Repo>, q: String) {
        mDataBase!!.repoModel().insertAll(repos)
        setFoundReposDataList(mDataBase!!.repoModel().loadByQuery(q))
    }

    private fun setFoundReposDataList(foundRepos: List<Repo>) {
        mFoundRepos.value = foundRepos
    }

    fun getReposCached(queryString: String) {
        setFoundReposDataList(mDataBase!!.repoModel().loadByQuery(queryString))
    }
}
