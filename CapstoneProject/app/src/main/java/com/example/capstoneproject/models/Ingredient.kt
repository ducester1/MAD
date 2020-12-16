package com.example.capstoneproject.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "IngredientsTable")
data class Ingredient(
    var image: String?,
    var name: String,
    var description: String,
    var amount: Int,
    var scale: String,
    var expireDate: Date,

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
)