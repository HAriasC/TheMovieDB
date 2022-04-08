package com.example.moviedbapp.framework.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviedbapp.framework.data.local.model.Pelicula

@Database(entities = [Pelicula::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun peliculaDao(): PeliculaDao
}