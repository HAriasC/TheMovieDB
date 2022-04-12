package com.example.moviedbapp.framework.data.remote

import com.example.moviedbapp.framework.data.remote.model.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDBService {

    @GET("movie/upcoming")
    suspend fun getPeliculasFromApiRest(@Query("page") page: Int = 1): Response
}