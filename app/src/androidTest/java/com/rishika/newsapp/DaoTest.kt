package com.rishika.newsapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.rishika.newsapp.data.News
import com.rishika.newsapp.database.AppDatabase
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by Rishika on 23/05/20.
 */
@RunWith(AndroidJUnit4::class)
class DaoTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    var database: AppDatabase? = null


    @Before
    @Throws(Exception::class)
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            AppDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
    }

    @After
    @Throws(Exception::class)
    fun closeDb() {
        database?.close()
    }


    @Test
    @Throws(InterruptedException::class)
    fun insert() {
        val list = arrayListOf<News>()
        list.add(UtilTest.newsResponse)
        database?.newsDao()?.insertNews(list)?.andThen {
            fetchList(list)
        }
    }


    fun fetchList(list: ArrayList<News>) {
        database?.newsDao()?.fetchNews()?.subscribeOn(Schedulers.newThread())?.doOnError {
        }?.subscribe {
            if(list.size>0) {
                assertTrue(list.get(0).id == it.get(0).id)
            }
        }
    }


}