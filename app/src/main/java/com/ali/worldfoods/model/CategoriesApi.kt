package com.ali.worldfoods.model

import io.reactivex.Single
import retrofit2.http.GET

interface CategoriesApi {
    @GET("categories.json")
    fun getCategories(): Single<ArrayList<Category>>
}