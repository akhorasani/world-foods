package com.ali.worldfoods.model



import com.ali.worldfoods.di.DaggerApiComponent
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class CategoriesService {

    @Inject
    lateinit var api: CategoriesApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getCategories(): Single<ArrayList<Category>> {
        return api.getCategories()
    }
}