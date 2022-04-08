package com.example.moviedbapp.data.source.local

import androidx.paging.PagingSource
import com.example.moviedbapp.domain.Pelicula
import com.example.moviedbapp.framework.data.local.model.Pelicula as PeliculaDao
import kotlinx.coroutines.flow.Flow

interface PeliculaLocalDataSource {

    fun findById(id: Int): Flow<Pelicula>
    fun getAll(): Flow<List<Pelicula>>
    fun getAllPaginated(): PagingSource<Int, PeliculaDao>
    suspend fun update(pelicula: Pelicula)
    suspend fun save(peliculas: List<Pelicula>)
    suspend fun delete(pelicula: Pelicula)
    suspend fun clear()
    suspend fun isEmpty(): Boolean
}