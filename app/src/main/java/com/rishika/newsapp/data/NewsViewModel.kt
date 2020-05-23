package com.rishika.newsapp.data

import androidx.lifecycle.ViewModel
import com.rishika.newsapp.database.NewsRepository
import javax.inject.Inject

/**
 * Created by Rishika on 23/05/20.
 */
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) :
    ViewModel() {
    override fun onCleared() {
        newsRepository.compositeDisposable.dispose()
        super.onCleared()
    }

    val list by lazy {
        newsRepository.observeList()
    }

    fun getData() {
        newsRepository.getData()
    }



}