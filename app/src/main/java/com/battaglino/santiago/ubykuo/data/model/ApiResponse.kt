package com.battaglino.santiago.ubykuo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Santiago Battaglino.
 */
class ApiResponse<T> {

    @SerializedName("total_count")
    @Expose
    var toalCount: Int = 0

    @SerializedName("incomplete_results")
    @Expose
    var incompleteResults: Boolean = false

    @SerializedName("items")
    @Expose
    var items: List<T> = emptyList()
}
