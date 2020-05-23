package com.rishika.newsapp.database

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.rishika.newsapp.data.News
import com.rishika.newsapp.network.NetworkState
import com.rishika.newsapp.network.NewsService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Rishika on 23/05/20.
 */
class NewsRepository @Inject constructor(
    private val newsService: NewsService,
    private val newsDao: NewsDao,
    val compositeDisposable: CompositeDisposable
) {

    fun observeList() = newsDao.fetchPagedNews().toLiveData(pagedListConfig())

    val networkState = MutableLiveData<NetworkState>()

    fun getData() {
        networkState.postValue(NetworkState.LOADING)
        newsService.getTopHeadlines("IN")
            .subscribeOn(Schedulers.io())
            .subscribe({
                delete(it.articles)
                networkState.postValue(NetworkState.LOADED)
            }, {
                networkState.postValue(NetworkState.error(it.localizedMessage ?: "Network Error Occur"))
            })
    }

    private fun store(list: List<News>) {
        val dispose = newsDao.insertNews(list)
            .subscribeOn(Schedulers.io())
            .subscribe({
            }, {})
        compositeDisposable.add(dispose)
    }

    private fun delete(list: List<News>) {
        val dispose = newsDao.deleteAllNews()
            .subscribeOn(Schedulers.io())
            .subscribe({
                store(list)
            }, {
            })
        compositeDisposable.add(dispose)
    }

    companion object {
        private const val PAGE_SIZE = 100
        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    }

}