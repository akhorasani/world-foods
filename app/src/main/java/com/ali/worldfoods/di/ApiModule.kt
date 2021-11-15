package com.ali.worldfoods.di

import com.ali.worldfoods.model.CategoriesApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private val BASE_URL = "https://worldfoods-18e60-default-rtdb.firebaseio.com/"

    @Provides
    fun provideCategoriesApi(): CategoriesApi {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(CategoriesApi::class.java)
    }
}