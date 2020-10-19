package com.example.madlevel4task2

import androidx.room.TypeConverter
import com.example.madlevel4task2.model.GameMoves
import com.example.madlevel4task2.model.GameResults
import java.util.*

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?) : Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun fromGameMoves(value: String?): GameMoves? {
        return value?.let { GameMoves.valueOf(it) }
    }

    @TypeConverter
    fun gameMovesToString(gameMove: GameMoves?): String? {
        return gameMove?.toString()
    }

    @TypeConverter
    fun fromGameResults(value: String?): GameResults? {
        return value?.let { GameResults.valueOf(it) }
    }

    @TypeConverter
    fun gameResultsToString(gameResult: GameResults?): String? {
        return gameResult?.toString()
    }
}