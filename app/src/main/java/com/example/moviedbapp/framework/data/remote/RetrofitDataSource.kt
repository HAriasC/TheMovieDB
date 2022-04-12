package com.example.moviedbapp.framework.data.remote

import android.util.Log
import com.example.moviedbapp.data.source.remote.PeliculaRemoteDataSource
import com.example.moviedbapp.domain.Pelicula
import com.example.moviedbapp.utils.toDomainPelicula

class RetrofitDataSource : PeliculaRemoteDataSource {

    override suspend fun getFromApiRest(page: Int): List<Pelicula> {
        return RetrofitService.service.getPeliculasFromApiRest(page).results.map { it.toDomainPelicula() }
    }
}