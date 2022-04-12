package com.example.moviedbapp.framework.data.remote

import com.example.moviedbapp.utils.API_KEY
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private val okHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(this)
            .addInterceptor(TokenInterceptor(API_KEY))
            .build()
    }

    val service: TheMovieDBService = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build().run {
            create(TheMovieDBService::class.java)
        }
}

class TokenInterceptor (private val apiKey: String) : Interceptor {

    companion object {
        const val API_KEY = "api_key"
    }
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var original = chain.request()
        val url = original.url.newBuilder()
            .addQueryParameter(API_KEY, apiKey)
            .build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}