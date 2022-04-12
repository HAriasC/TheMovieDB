package com.example.moviedbapp.data.source.remote

import com.example.moviedbapp.domain.Pelicula

interface PeliculaRemoteDataSource {

    suspend fun getFromApiRest(page: Int = 1): List<Pelicula>
}