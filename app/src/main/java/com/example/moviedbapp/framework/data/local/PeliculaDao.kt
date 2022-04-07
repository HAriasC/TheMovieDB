package com.example.moviedbapp.framework.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PeliculaDao {

    @Query("SELECT * FROM pelicula")
    fun getAll(): Flow<List<Pelicula>>

    @Query("SELECT * FROM pelicula WHERE id = :id")
    fun findById(id: Int): Flow<Pelicula>

    @Query("SELECT COUNT(id) FROM pelicula")
    fun size(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(peliculas: List<Pelicula>)

    @Update
    fun update(pelicula: Pelicula)

    @Delete
    fun delete(vararg peliculas: Pelicula)
}