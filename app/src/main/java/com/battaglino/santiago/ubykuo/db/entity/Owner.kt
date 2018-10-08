package com.battaglino.santiago.ubykuo.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.reactivex.annotations.NonNull
import org.parceler.Parcel

/**
 * Created by Santiago Battaglino.
 */
@Parcel
@Entity(tableName = "owners", indices = [Index(value = arrayOf("id"))])
data class Owner(

        val login: String,

        @PrimaryKey(autoGenerate = true)
        @NonNull
        @Expose
        val id: Int,

        @SerializedName("node_id")
        val nodeId: String,
        @SerializedName("avatar_url")
        val avatarUrl: String,
        @SerializedName("gravatar_id")
        val gravatarId: String,
        val url: String,
        @SerializedName("received_events_url")
        val receivedEventsUrl: String,
        val type: String
)