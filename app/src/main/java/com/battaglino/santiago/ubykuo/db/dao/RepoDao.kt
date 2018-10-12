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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repo: Repo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(repos: List<Repo>)

    @Query("select * from repos")
    fun loadList(): LiveData<List<Repo>>

    @Query("select * from repos where uid = :uid")
    fun load(uid: Int?): LiveData<Repo>

    @Query("select name, score, fullname  from repos where name like '%' || :query  || '%' or fullName like '%' || :query  || '%' order by score desc")
    fun loadByQuery(query: String?): List<Repo>

    @Query("select name, fullName from repos order by score desc")
    fun loadSuggestions(): List<Repo>
}
