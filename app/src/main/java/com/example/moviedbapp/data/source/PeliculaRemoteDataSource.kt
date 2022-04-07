package com.example.moviedbapp.data.source

import com.example.moviedbapp.domain.Pelicula

interface PeliculaRemoteDataSource {

    suspend fun getFromApiRest(apiKey: String): List<Pelicula>
}