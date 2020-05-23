package com.rishika.newsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rishika.newsapp.data.News
import com.rishika.newsapp.utils.DateConverter

/**
 * Created by Rishika on 23/05/20.
 */

@TypeConverters(DateConverter::class)
@Database(entities = [News::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context) = instance
            ?: synchronized(AppDatabase::class) {
            instance
                ?: createDatabase(
                    context
                ).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "news-db")
                .build()


    }

}