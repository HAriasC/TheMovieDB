package com.example.moviedbapp.data.source

import com.example.moviedbapp.domain.Pelicula
import kotlinx.coroutines.flow.Flow

interface PeliculaLocalDataSource {

    fun findById(id: Int): Flow<Pelicula>
    fun getAll(): Flow<List<Pelicula>>
    suspend fun update(pelicula: Pelicula)
    suspend fun save(peliculas: List<Pelicula>)
    suspend fun delete(pelicula: Pelicula)
    suspend fun clear()
    suspend fun isEmpty(): Boolean
}