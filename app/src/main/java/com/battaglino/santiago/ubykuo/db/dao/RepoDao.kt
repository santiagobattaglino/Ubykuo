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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(repo: Repo)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(repos: List<Repo>)

    @Query("select * from repos")
    fun loadList(): LiveData<List<Repo>>

    @Query("select * from repos where id = :id")
    fun load(id: Int?): LiveData<Repo>

    @Query("select id, name, score, fullname from repos where name like '%' || :query  || '%' or fullName like '%' || :query  || '%' or description like '%' || :query  || '%' order by score desc")
    fun loadByQuery(query: String?): List<Repo>

    @Query("select id, name, fullName, description from repos order by score desc")
    fun loadSuggestions(): LiveData<List<Repo>>
}
