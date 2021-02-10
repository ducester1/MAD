package com.example.capstoneproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.R
import com.example.capstoneproject.models.Ingredient
import com.example.capstoneproject.ui.WarehouseFragmentDirections
import kotlinx.android.synthetic.main.item_ingredient.view.*

class IngredientAdapter (private val ingredients: List<Ingredient>): RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {

    var onItemClick: ((Ingredient) -> Unit)? = null
    var items: List<Ingredient> = emptyList()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun databind(ingredient: Ingredient) {
            //itemView.tv_ingredient_card_image =
            itemView.tv_ingredient_card_name.text = ingredient.name
            itemView.tv_ingredient_card_amount.text = ingredient.amount.toString()
            itemView.tv_ingredient_card_scale.text = ingredient.scale

        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(ingredients[adapterPosition])
            }
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