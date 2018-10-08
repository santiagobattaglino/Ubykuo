package com.battaglino.santiago.ubykuo.data.service;

import com.battaglino.santiago.ubykuo.data.model.ApiResponse;
import com.battaglino.santiago.ubykuo.db.entity.Repo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Santiago Battaglino.
 */
public interface ApiService {

    @GET("search/repositories")
    Observable<ApiResponse<Repo>> getRepos(
            @Query("q") String q,
            @Query("sort") String sort,
            @Query("order") String order
    );
}
