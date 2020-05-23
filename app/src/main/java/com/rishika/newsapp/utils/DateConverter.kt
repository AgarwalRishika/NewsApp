package com.rishika.newsapp.utils

import androidx.room.TypeConverter
import java.util.*

/**
 * Created by Rishika on 23/05/20.
 */
class DateConverter {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}

