package com.ali.worldfoods.di

import com.ali.worldfoods.model.CategoriesService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: CategoriesService)
}