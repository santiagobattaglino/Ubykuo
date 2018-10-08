package com.battaglino.santiago.ubykuo.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

import com.battaglino.santiago.ubykuo.db.entity.Repo

/**
 * Created by Santiago Battaglino.
 */
@Dao
interface RepoDao {

    @Query("select * from repos")
    fun loadList(): LiveData<List<Repo>>

    @Insert
    fun insertAll(repos: List<Repo>)
}
