package com.ali.worldfoods.model

import com.google.gson.annotations.SerializedName

data class Food(
//        @SerializedName("creation_time_ms")
//        var creationTimeMs: Long = 0,
        @SerializedName("img")
        val image: String? = null,
        @SerializedName("link")
        val link: String? = null,
        @SerializedName("title")
        val name: String? = null
)
// first git change