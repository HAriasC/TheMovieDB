package com.example.moviedbapp.data.repositories

import androidx.paging.PagingSource
import com.example.moviedbapp.data.source.local.PeliculaLocalDataSource
import com.example.moviedbapp.data.source.remote.PeliculaRemoteDataSource
import com.example.moviedbapp.domain.Pelicula
import com.example.moviedbapp.framework.data.local.model.Pelicula as PeliculaDao
import kotlinx.coroutines.flow.Flow

class PeliculasRepository(
    private val peliculaLocalDataSource: PeliculaLocalDataSource,
    private val peliculaRemoteDataSource: PeliculaRemoteDataSource,
    private val apiKey: String
) {

    suspend fun getPeliculas(): Flow<List<Pelicula>> {
        if (peliculaLocalDataSource.isEmpty()) {
            peliculaLocalDataSource.save(peliculaRemoteDataSource.getFromApiRest(apiKey = apiKey))
        }
        return peliculaLocalDataSource.getAll()
    }

    suspend fun getPeliculasPaginated(): PagingSource<Int, PeliculaDao> {
        return peliculaLocalDataSource.getAllPaginated()
    }

    suspend fun findById(id: Int): Flow<Pelicula> = peliculaLocalDataSource.findById(id)

    fun getLocalSource() = peliculaLocalDataSource

    fun getRomoteSource() = peliculaRemoteDataSource
}