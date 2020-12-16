package com.example.capstoneproject.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "RecipesTable")
data class Recipe(
        var image: String?,
        var name: String,
        var description: String?,
        var categories: MutableList <String>?,
        var ingredientsId: MutableList <Long>?,
        var preperation: String?,

        @PrimaryKey(autoGenerate = true)
        var id: Long? = null
)