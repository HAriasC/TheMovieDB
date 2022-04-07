package com.example.moviedbapp.framework.data.remote

import com.example.moviedbapp.data.source.PeliculaRemoteDataSource
import com.example.moviedbapp.domain.Pelicula
import com.example.moviedbapp.utils.toDomainPelicula

class RetrofitDataSource : PeliculaRemoteDataSource {

    override suspend fun getFromApiRest(page: Int, apiKey: String): List<Pelicula> {
        return RetrofitService.service.getPeliculasFromApiRest(page, apiKey).results.map { it.toDomainPelicula() }
    }
}