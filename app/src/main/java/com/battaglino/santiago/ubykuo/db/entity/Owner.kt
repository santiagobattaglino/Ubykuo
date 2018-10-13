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
@Entity(tableName = "owners", indices = [Index(value = arrayOf("id"))])
data class Owner(

        @PrimaryKey(autoGenerate = true)
        @NonNull
        @Expose
        var id: Int?,

        @Expose
        var login: String?,

        @Expose
        @SerializedName("node_id")
        val nodeId: String?,

        @Expose
        @SerializedName("avatar_url")
        val avatarUrl: String?,

        @Expose
        @SerializedName("gravatar_id")
        val gravatarId: String?,

        @Expose
        var url: String?,

        @Expose
        @SerializedName("received_events_url")
        val receivedEventsUrl: String?,

        @Expose
        var type: String?
) : Parcelable {
    constructor() : this(0, "", "", "", "", "", "", "")
}