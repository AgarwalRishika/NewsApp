package com.rishika.newsapp.network

import com.rishika.newsapp.database.NewsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Rishika on 23/05/20.
 */
interface NewsService {

    @GET("v2/top-headlines")
    fun getTopHeadlines(@Query("country") country: String): Single<NewsResponse>



}