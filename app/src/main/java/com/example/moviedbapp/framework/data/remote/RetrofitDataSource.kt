package com.example.moviedbapp.framework.data.remote

import com.example.moviedbapp.data.source.PeliculaRemoteDataSource
import com.example.moviedbapp.domain.Pelicula
import com.example.moviedbapp.utils.toDomainPelicula

class RetrofitDataSource : PeliculaRemoteDataSource {

    override suspend fun getFromApiRest(apiKey: String): List<Pelicula> {
        return RetrofitService.service.getPeliculasFromApiRest(apiKey).result.map { it.toDomainPelicula() }
    }
}