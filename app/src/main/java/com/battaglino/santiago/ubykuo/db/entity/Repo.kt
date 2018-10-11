package com.battaglino.santiago.ubykuo.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.reactivex.annotations.NonNull
import kotlinx.android.parcel.Parcelize

/**
 * Created by Santiago Battaglino.
 */
@Parcelize
@Entity(tableName = "repos", indices = [Index(value = arrayOf("id"))])
data class Repo(

        @Expose
        @PrimaryKey(autoGenerate = true)
        @NonNull
        var id: Int,

        @Expose
        @SerializedName("node_id")
        var nodeId: String,

        @Expose
        var name: String,

        @Expose
        @SerializedName("full_name")
        var fullName: String,

        @Expose
        var owner: Owner = Owner(),

        @Expose
        var private: Boolean = false,

        @Expose
        @SerializedName("html_url")
        var htmlUrl: String,

        @Expose
        var description: String,

        @Expose
        var fork: Boolean,

        @Expose
        var url: String,

        @Expose
        @SerializedName("created_at")
        var createdAt: String,

        @Expose
        @SerializedName("updated_at")
        var updatedAt: String,

        @Expose
        @SerializedName("pushed_at")
        var pushedAt: String,

        @Expose
        var homepage: String?,

        @Expose
        var size: Int,

        @Expose
        @SerializedName("stargazers_count")
        var stargazersCount: Int,

        @Expose
        @SerializedName("watchers_count")
        var watchersCount: Int,

        @Expose
        var language: String?,

        @Expose
        @SerializedName("forks_count")
        var forksCount: Int,

        @Expose
        @SerializedName("open_issues_count")
        var openIssuesCount: Int,

        @Expose
        @SerializedName("master_branch")
        var masterBranch: String,

        @Expose
        @SerializedName("default_branch")
        var defaultBranch: String,

        @Expose
        var score: Double
) : Parcelable {
    constructor() : this(0, "", "", "", Owner(), false, "",
            "", false, "", "", "", "", "",
            0, 0, 0, "", 0, 0,
            "", "", 0.0)
}