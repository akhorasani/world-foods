package com.ali.worldfoods.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ali.worldfoods.R
import com.ali.worldfoods.model.Category
import com.ali.worldfoods.model.Food
import com.ali.worldfoods.viewModel.ListViewModel
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ListViewModel
    private val categoriesAdapter = CategoryAdapter(this, arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        rvCategory.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = categoriesAdapter
        }

        observeViewModel()

//        var firebaseDatabase = FirebaseDatabase.getInstance()
//        var databaseRef = firebaseDatabase.reference
        /*
        val database = Firebase.database
        val myRef = database.getReference("categories").push()
        var food = Food(
                "Ghormeh Sabzi",
                "https://surfiran.com/wp-content/uploads/2016/10/Khoresht-e-ghormeh-sabzi-495x400.jpg",
                "Ox_pfuDY-j8"
                        )
        var category = Category(
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
        myRef.setValue(category)

         */
//        databaseRef.setValue("Hello Ali!")
    }

    fun observeViewModel(){

        viewModel.foodCategories.observe(this, Observer { categories ->
            categories?.let {
                rvCategory.visibility = View.VISIBLE
                categoriesAdapter.updateCategories(it)
            }
        })

        viewModel.categoryLoadError.observe(this, Observer{ isError ->
            isError?.let { list_error.visibility = if(it) View.VISIBLE else View.GONE }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loading_view.visibility = if(it) View.VISIBLE else View.GONE
                if(it){
                    list_error.visibility = View.GONE
                    rvCategory.visibility = View.GONE
                }
            }
        })
    }
}