package com.battaglino.santiago.ubykuo.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Santiago Battaglino.
 */
public class ApiResponse<T> {

    @SerializedName("total_count")
    @Expose
    public int toalCount;

    @SerializedName("incomplete_results")
    @Expose
    public boolean incompleteResults;

    @SerializedName("items")
    @Expose
    public List<T> items;
}
