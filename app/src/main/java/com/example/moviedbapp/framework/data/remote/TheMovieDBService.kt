package com.example.moviedbapp.framework.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDBService {

    @GET("movie/upcoming")
    suspend fun getPeliculasFromApiRest(@Query("page") page: Int = 1, @Query("api_key") apiKey: String): Result
}