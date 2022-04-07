package com.example.moviedbapp.framework.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDBService {

    @GET("movie/upcoming?page=1")
    suspend fun getPeliculasFromApiRest(@Query("api_key") apiKey: String): Result
}