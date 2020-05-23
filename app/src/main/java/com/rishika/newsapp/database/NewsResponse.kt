package com.rishika.newsapp.database

import com.rishika.newsapp.data.News

/**
 * Created by Rishika on 23/05/20.
 */

data class NewsResponse(val status: String, val totalResults: Int, val articles: List<News>)