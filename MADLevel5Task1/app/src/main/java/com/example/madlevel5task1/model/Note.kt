package com.example.madlevel5task1.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "NoteTable")
class Note (
    var title: String,
    var date: Date,
    var text: String,
    @PrimaryKey
    var id: Long ) {

}