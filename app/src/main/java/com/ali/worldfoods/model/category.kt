package com.ali.worldfoods.model

import com.google.gson.annotations.SerializedName

data class Category(
        @SerializedName("title")
        val title: String? = null,
        @SerializedName("foods")
        val foods: ArrayList<Food>? = null
)