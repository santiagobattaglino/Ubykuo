package com.battaglino.santiago.ubykuo.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

import com.battaglino.santiago.ubykuo.db.entity.Repo

/**
 * Created by Santiago Battaglino.
 */
@Dao
interface RepoDao {

    @Query("select * from repos")
    fun loadList(): LiveData<List<Repo>>

    @Query("select * from repos where name like '%' || :query  || '%' or fullName like '%' || :query  || '%'")
    fun loadByName(query: String?): List<Repo>

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insertAll(repos: List<Repo>)
}
