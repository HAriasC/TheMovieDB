package com.example.moviedbapp.framework.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviedbapp.framework.data.local.model.Pelicula
import com.example.moviedbapp.framework.data.local.model.RemoteKeys
import com.example.moviedbapp.framework.data.local.model.Response

@Database(entities = [Response::class, Pelicula::class, RemoteKeys::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getPeliculaDao(): PeliculaDao
    abstract fun getRemoteKeysDao(): RemoteKeysDao
}