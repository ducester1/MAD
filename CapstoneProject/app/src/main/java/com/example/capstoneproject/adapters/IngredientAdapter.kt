package com.example.capstoneproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.R
import com.example.capstoneproject.models.Ingredient
import kotlinx.android.synthetic.main.item_ingredient.view.*

class IngredientAdapter(private val ingredients: List<Ingredient>): RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun databind(ingredient: Ingredient) {
            //itemView.tv_ingredient_card_image =
            itemView.tv_ingredient_card_name.text = ingredient.name
            itemView.tv_ingredient_card_amount.text = ingredient.amount.toString()
            itemView.tv_ingredient_card_scale.text = ingredient.scale
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_ingredient, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(ingredients[position])
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }
}