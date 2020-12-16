package com.example.capstoneproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.R
import com.example.capstoneproject.models.Recipe
import kotlinx.android.synthetic.main.item_recipe.view.*

class RecipeAdapter(private val recipies: List<Recipe>): RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun databind(recipe: Recipe) {
            //todo: itemView.tv_ingredient_card_image =
            itemView.tv_recipe_card_name.text = recipe.name
            itemView.tv_recipe_card_description.text = recipe.description.toString()
            //todo: action chips
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(recipies[position])
    }

    override fun getItemCount(): Int {
        return recipies.size
    }
}