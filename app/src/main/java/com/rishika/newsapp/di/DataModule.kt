package com.rishika.newsapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rishika.newsapp.BuildConfig
import com.rishika.newsapp.utils.CommonConstants
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Rishika on 23/05/20.
 */
@Module
class DataModule {

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(CommonConstants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(CommonConstants.READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(CommonConstants.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()


    @Provides
    @Singleton
    fun provideGson() = GsonBuilder().serializeNulls().setPrettyPrinting().create()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson) = GsonConverterFactory.create(gson)

    @Provides
    fun provideExecutor(): Executor = Executors.newFixedThreadPool(5)

    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()
    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
    }


}
