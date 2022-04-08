package com.example.moviedbapp.framework.data.local

import androidx.paging.PagingSource
import androidx.room.*
import com.example.moviedbapp.framework.data.local.model.Pelicula
import kotlinx.coroutines.flow.Flow

@Dao
interface PeliculaDao {

    @Query("SELECT * FROM pelicula")
    fun getAll(): Flow<List<Pelicula>>

    @Query("SELECT * FROM pelicula")
    fun getAllPaginated(): PagingSource<Int, Pelicula>

    @Query("SELECT * FROM pelicula WHERE id = :id")
    fun findById(id: Int): Flow<Pelicula>

    @Query("SELECT COUNT(id) FROM pelicula")
    fun size(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(peliculas: List<Pelicula>)

    @Update
    suspend fun update(pelicula: Pelicula)

    @Delete
    suspend fun delete(vararg peliculas: Pelicula)
}