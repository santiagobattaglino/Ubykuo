package com.battaglino.santiago.ubykuo.data.service

import com.battaglino.santiago.ubykuo.data.model.ApiResponse
import com.battaglino.santiago.ubykuo.db.entity.Repo

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Santiago Battaglino.
 */
interface ApiService {

    @GET("search/repositories")
    fun getRepos(
            @Query("q") q: String,
            @Query("sort") sort: String?,
            @Query("order") order: String?,
            @Query("page") page: String?,
            @Query("per_page") perPage: String?
    ): Observable<ApiResponse<Repo>>
}
