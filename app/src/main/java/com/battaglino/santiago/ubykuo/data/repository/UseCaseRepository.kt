package com.battaglino.santiago.ubykuo.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context

/**
 * Created by Santiago Battaglino.
 *
 * @typeparam T This represents your model entity
 */
abstract class UseCaseRepository<T>(protected var context: Context) {

    var mContext: Context? = context

    /* LiveData to manage a list of T elements */
    /* method that returns the local LiveData list. This method must be observed in your view */
    var mDataList: LiveData<List<T>> = MutableLiveData()

    /* LiveData to manage a T element */
    /* method that returns the local LiveData. This method must be observed in your view */
    var mData: LiveData<T> = MutableLiveData()

    init {
        this.initLocalData()
    }

    /* Initialize and configure local Room database  */
    abstract fun initLocalData()

    /* Add a T element into the databse */
    abstract fun addData(data: T)

    /* Add a list of T into the database */
    abstract fun addDataList(dataList: List<T>)

    /* Implements the logic to request the data to the server with RxAndroid */
    abstract fun requestDataToServer()
}
