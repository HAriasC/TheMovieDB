package com.example.moviedbapp.data.repositories

import com.example.moviedbapp.data.source.PeliculaLocalDataSource
import com.example.moviedbapp.data.source.PeliculaRemoteDataSource
import com.example.moviedbapp.domain.Pelicula
import kotlinx.coroutines.flow.Flow

class PeliculasRepository(
    private val peliculaLocalDataSource: PeliculaLocalDataSource,
    private val peliculaRemoteDataSource: PeliculaRemoteDataSource,
    private val apiKey: String
) {

    suspend fun getPeliculas(): Flow<List<Pelicula>> {
        if (peliculaLocalDataSource.isEmpty()) {
            peliculaRemoteDataSource.getFromApiRest(apiKey)
        }
        return peliculaLocalDataSource.getAll()
    }

    suspend fun findById(id: Int): Flow<Pelicula> = peliculaLocalDataSource.findById(id)
}