package com.example.madlevel5task2.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "GameBacklogTable")
data class Game(
    var title: String,
    var platforms: String,
    var releaseDate: Date,

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
)