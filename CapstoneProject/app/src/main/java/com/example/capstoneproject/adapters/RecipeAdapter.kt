package com.example.capstoneproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.R
import com.example.capstoneproject.models.Recipe
import com.example.capstoneproject.ui.RecipesFragmentDirections
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_add_recipe.view.*
import kotlinx.android.synthetic.main.item_recipe.view.*

class RecipeAdapter(private val recipies: List<Recipe>): RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun databind(recipe: Recipe) {
            //todo: itemView.tv_ingredient_card_image =
            itemView.tv_recipe_card_name.text = recipe.name
            itemView.tv_recipe_card_description.text = recipe.description.toString()
            itemView.cg_recipe_card_categories.removeAllViews()
            for (index in 0 until recipe.categories?.count()!!) {
                val chip = Chip(itemView.context)
                chip.text = recipe.categories!![index]
                itemView.cg_recipe_card_categories.addView(chip)
            }

            itemView.setOnClickListener {
                val action = RecipesFragmentDirections.actionRecipesFragmentToAddRecipeFragment(recipe.id!!)
                itemView.findNavController().navigate(action)
            }
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