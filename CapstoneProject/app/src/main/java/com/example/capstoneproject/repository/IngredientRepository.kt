package com.example.capstoneproject.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.capstoneproject.dao.IngredientDao
import com.example.capstoneproject.databases.IngredientDatabase
import com.example.capstoneproject.models.Ingredient
import com.example.capstoneproject.models.Recipe

class IngredientRepository(context: Context) {
    private val ingredientDao: IngredientDao

    init {
        val database = IngredientDatabase.getDatabase(context)
        ingredientDao = database!!.ingredientDao()
    }

    fun getAllIngredients(): LiveData<List<Ingredient>> {
        return ingredientDao.getAllIngredients()
    }

    fun getIngredient(id: Long): LiveData<Ingredient> {
        return ingredientDao.getIngredient(id)
    }

    suspend fun addIngredient(ingredient: Ingredient) {
        ingredientDao.addIngredient(ingredient)
    }

    suspend fun updateIngredient(ingredient: Ingredient) {
        ingredientDao.updateIngredient(ingredient)
    }

    suspend fun deleteIngredient(ingredient: Ingredient) {
        ingredientDao.deleteIngredient(ingredient)
    }

    suspend fun deleteAllIngredients() {
        ingredientDao.deleteAllIngredients()
    }

}