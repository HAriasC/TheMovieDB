package com.example.moviedbapp.data.source

import com.example.moviedbapp.domain.Pelicula

interface PeliculaRemoteDataSource {

    suspend fun getFromApiRest(page: Int = 1, apiKey: String): List<Pelicula>
}