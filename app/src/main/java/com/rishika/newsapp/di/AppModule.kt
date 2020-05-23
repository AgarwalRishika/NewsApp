package com.rishika.newsapp.di

import android.app.Application
import com.rishika.newsapp.BuildConfig
import com.rishika.newsapp.utils.CommonConstants
import com.rishika.newsapp.network.AuthenticateInterceptor
import com.rishika.newsapp.network.NewsService
import com.rishika.newsapp.database.AppDatabase
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Rishika on 23/05/20.
 */
@Module(includes = [DataModule::class,ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideNewsApiClient(
        @NewsApi okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ) =
        provideService(
           CommonConstants.BASE_URL,
            okHttpClient,
            gsonConverterFactory,
            NewsService::class.java
        )


    @Provides
    @NewsApi
    fun provideAuthHttpClient(okHttpClient: OkHttpClient) =
        okHttpClient.newBuilder().addInterceptor(AuthenticateInterceptor(BuildConfig.API_KEY))
            .build()

    @Singleton
    @Provides
    fun provideDb(app: Application) = AppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideNewsDao(db: AppDatabase) = db.newsDao()

    private fun createRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ) = Retrofit.Builder()
        .addConverterFactory(converterFactory)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .baseUrl(baseUrl)
        .build()

    private fun <T> provideService(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        clazz: Class<T>
    ) = createRetrofit(baseUrl, okHttpClient, gsonConverterFactory).create(clazz)

}