package com.example.moviedbapp.framework.data.remote

import com.example.moviedbapp.domain.Usuario
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthService {

    @GET("login")
    suspend fun login(@Query("user") usuario: String, @Query("pass") pass: String): Response<Usuario>
}