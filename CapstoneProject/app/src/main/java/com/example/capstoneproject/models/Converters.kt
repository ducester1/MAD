package com.example.capstoneproject.models

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.JsonArray
import java.util.*

class Converters {

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun timestampToDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun fromString(stringListString: String?): List<String>? {
        if (stringListString != null) return stringListString.split("^").map { it }
        return null
    }

    @TypeConverter
    fun toString(stringList: List<String>?): String? {
        if (stringList != null) return stringList.joinToString(separator = "^")
        return null
    }

    @TypeConverter
    fun gettingListFromString(genreIds: String?): List<Long>? {
        if(genreIds != null) {
            val list = mutableListOf<Long>()

            val array = genreIds.split(",".toRegex()).dropLastWhile {
                it.isEmpty()
            }.toTypedArray()

            for (s in array) {
                if (s.isNullOrEmpty()) {
                    list.add(s.toLong())
                }
            }
            return list
        }
        return null
    }

    @TypeConverter
    fun writingStringFromList(list: List<Long>?): String? {
        if (list != null) {
            var genreIds = ""
            for (i in list) genreIds += ",$i"
            return genreIds
        }
        return null
    }}