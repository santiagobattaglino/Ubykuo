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

        @PrimaryKey(autoGenerate = true)
        @NonNull
        @Expose
        var id: Int = 0,

        @SerializedName("node_id")
        var nodeId: String = "",
        var name: String = "",
        @SerializedName("full_name")
        var fullName: String = "",
        var owner: Owner = Owner(),
        var private: Boolean = false,
        @SerializedName("html_url")
        var htmlUrl: String = "",
        var description: String = "",
        var fork: Boolean = false,
        var url: String = "",
        @SerializedName("created_at")
        var createdAt: String = "",
        @SerializedName("updated_at")
        var updatedAt: String = "",
        @SerializedName("pushed_at")
        var pushedAt: String = "",
        var homepage: String = "",
        var size: Int = 0,
        @SerializedName("stargazers_count")
        var stargazersCount: Int = 0,
        @SerializedName("watchers_count")
        var watchersCount: Int = 0,
        var language: String = "",
        @SerializedName("forks_count")
        var forksCount: Int = 0,
        @SerializedName("open_issues_count")
        var openIssuesCount: Int = 0,
        @SerializedName("master_branch")
        var masterBranch: String = "",
        @SerializedName("default_branch")
        var defaultBranch: String = "",
        var score: Double = 0.0
) : Parcelable