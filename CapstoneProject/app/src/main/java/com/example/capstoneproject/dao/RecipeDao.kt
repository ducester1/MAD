package com.example.capstoneproject.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.capstoneproject.models.Recipe

@Dao
interface RecipeDao {

    @Query("SELECT * FROM RecipesTable")
    fun getAllRecipes(): LiveData<List<Recipe>>

    @Insert
    suspend fun addRecipe(recipe: Recipe)

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

    @Query("DELETE FROM RecipesTable")
    suspend fun deleteAllRecipes()

}