package com.example.moviedbapp.data.repositories

import androidx.paging.PagingSource
import com.example.moviedbapp.data.source.local.PeliculaLocalDataSource
import com.example.moviedbapp.data.source.remote.PeliculaRemoteDataSource
import com.example.moviedbapp.domain.Pelicula
import com.example.moviedbapp.framework.data.local.AppDatabase
import com.example.moviedbapp.framework.data.local.model.Pelicula as PeliculaDao
import kotlinx.coroutines.flow.Flow

class PeliculasRepository(
    private val database: AppDatabase,
    private val peliculaLocalDataSource: PeliculaLocalDataSource,
    private val peliculaRemoteDataSource: PeliculaRemoteDataSource
) {

    suspend fun getPeliculas(): Flow<List<Pelicula>> {
        if (peliculaLocalDataSource.isEmpty()) {
            peliculaLocalDataSource.save(peliculaRemoteDataSource.getFromApiRest())
        }
        return peliculaLocalDataSource.getAll()
    }

    fun getPeliculasPaginatedSource(): PagingSource<Int, PeliculaDao> {
        return peliculaLocalDataSource.getAllPaginated()
    }

    fun findById(id: Int): Flow<Pelicula> = peliculaLocalDataSource.findById(id)

    fun getDatabase() = database

    fun getLocalSource() = peliculaLocalDataSource

    fun getRomoteSource() = peliculaRemoteDataSource
}