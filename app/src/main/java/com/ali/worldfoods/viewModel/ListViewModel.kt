package com.ali.worldfoods.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ali.worldfoods.model.CategoriesService
import com.ali.worldfoods.model.Category
import com.ali.worldfoods.model.Food
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class ListViewModel : ViewModel() {

    private val categoriesService = CategoriesService()

    // for closing the connection after retrieving data
    private val disposable = CompositeDisposable()

    val foodCategories = MutableLiveData<ArrayList<Category>>()
    val categoryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCategories()
    }

    private fun fetchCategories() {
        /*
        val mockData: ArrayList<Category> = arrayListOf<Category>(
            Category(
                "Iranian Foods",
                arrayListOf(Food(
                        "Ghormeh Sabzi",
                        "https://surfiran.com/wp-content/uploads/2016/10/Khoresht-e-ghormeh-sabzi-495x400.jpg",
                        "Ox_pfuDY-j8"
                ), Food(
                        "Kuku Sabzi",
                        "https://surfiran.com/wp-content/uploads/2016/10/Khoresht-e-ghormeh-sabzi-495x400.jpg",
                        "Ox_pfuDY-j8"
                ))
        )
        )
        categoryLoadError.value = false
        loading.value = false
        foodCategories.value = mockData
        */

        loading.value = true

        disposable.add(
                categoriesService.getCategories()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object :
                                DisposableSingleObserver<ArrayList<Category>>() {

                            override fun onSuccess(value: ArrayList<Category>?) {
                                foodCategories.value = value
                                categoryLoadError.value = false
                                loading.value = false
                            }

                            override fun onError(e: Throwable?) {
                                Log.d("myError","64")
                                categoryLoadError.value = true
                                loading.value = false
                            }
                        })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}