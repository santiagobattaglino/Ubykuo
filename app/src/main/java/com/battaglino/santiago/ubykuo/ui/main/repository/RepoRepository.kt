package com.battaglino.santiago.ubykuo.ui.main.repository

import android.app.Application
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

    override fun initLocalData() {
        mDataBase = AppDatabase.getDatabaseBuilder(context)
        mDataList = mDataBase!!.repoModel().loadList()
    }

    override fun addData(data: Repo) {

    }

    override fun addDataList(dataList: List<Repo>) {
        mDataBase!!.repoModel().insertAll(dataList)
        mDataList = mDataBase!!.repoModel().loadList()
    }

    override fun requestDataToServer() {
        mClient.getRepos("kotlin", null, null)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<ApiResponse<Repo>> {
                    override fun onSubscribe(d: Disposable) {
                        mDisposable.add(d)
                    }

                    override fun onNext(dataListFromServer: ApiResponse<Repo>) {
                        addDataList(dataListFromServer.items!!)
                        mDisposable.dispose()
                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onComplete() {

                    }
                })
    }
}
