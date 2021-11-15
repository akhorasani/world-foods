package com.ali.worldfoods.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ali.worldfoods.R
import com.ali.worldfoods.model.Category
import kotlinx.android.synthetic.main.single_category.view.*

class CategoryAdapter(var context: Context, var categories: ArrayList<Category>): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    fun updateCategories(newCategories: ArrayList<Category>){
        categories.clear()
        categories.addAll(newCategories)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CategoryViewHolder (
            LayoutInflater.from(parent.context).inflate(R.layout.single_category, parent, false)
    )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.recyclerView.adapter = FoodAdapter(context, categories[position].foods)
        holder.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.recyclerView.setHasFixedSize(true)
        holder.bind(categories[position])
    }

    override fun getItemCount() = categories.size

    class CategoryViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val recyclerView: RecyclerView = view.rvFoods
        val category_name = view.categoryName

        fun bind(category: Category){
            category_name.text = category.title
        }
    }
}