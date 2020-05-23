package com.rishika.newsapp.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rishika.newsapp.data.News
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * Created by Rishika on 23/05/20.
 */
@Dao
interface NewsDao {

    @Query("SELECT * FROM News")
    fun fetchNews(): Observable<List<News>>

    @Query("SELECT * FROM News")
    fun fetchPagedNews(): DataSource.Factory<Int, News>

    @Query("DELETE FROM News")
    fun deleteAllNews(): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(list: List<News>): Completable

}