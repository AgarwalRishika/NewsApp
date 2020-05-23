package com.rishika.newsapp.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Rishika on 23/05/20.
 */
class AuthenticateInterceptor(private val token: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(request)
    }

}