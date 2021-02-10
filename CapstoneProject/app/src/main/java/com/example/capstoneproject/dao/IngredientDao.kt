package com.example.capstoneproject.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.capstoneproject.models.Ingredient
import com.example.capstoneproject.models.Recipe

@Dao
interface IngredientDao {

    @Query("SELECT * FROM IngredientsTable")
    fun getAllIngredients(): LiveData<List<Ingredient>>

    @Query("Select * FROM IngredientsTable WHERE id = :id")
    fun getIngredient(id: Long): LiveData<Ingredient>

    @Insert
    suspend fun addIngredient(ingredient: Ingredient)

    @Update
    suspend fun updateIngredient(ingredient: Ingredient)

    @Delete
    suspend fun deleteIngredient(ingredient: Ingredient)

    @Query("DELETE FROM IngredientsTable")
    suspend fun deleteAllIngredients()

}