package com.ali.worldfoods.view



import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ali.worldfoods.R
import com.ali.worldfoods.model.Food
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.single_food.view.*


class FoodAdapter(var context: Context, var foods: ArrayList<Food>?): RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FoodViewHolder (
            LayoutInflater.from(parent.context).inflate(R.layout.single_food, parent, false)
    )

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(foods!![position])
    }

    override fun getItemCount() = foods!!.size

    inner class FoodViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val food_name = view.foodName
        val food_image = view.foodImage

        fun bind(food: Food){
            food_name.text = food.name
            Picasso.get().load(food.image).into(food_image)

            itemView.setOnClickListener {
//                Toast.makeText(context, food_name.text, Toast.LENGTH_LONG).show()
                var intent = Intent(context, DetailsActivity::class.java)
                intent.putExtra("foodName", food_name.text)
                intent.putExtra("foodImage", food.image.toString())
                intent.putExtra("foodLink", food.link.toString())

                context.startActivity(intent)
            }
        }
    }
}