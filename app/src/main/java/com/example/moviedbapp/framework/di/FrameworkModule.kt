package com.example.moviedbapp.framework.di

import android.app.Application
import androidx.room.Room
import com.example.moviedbapp.R
import com.example.moviedbapp.data.source.local.PeliculaLocalDataSource
import com.example.moviedbapp.data.source.remote.PeliculaRemoteDataSource
import com.example.moviedbapp.framework.data.local.AppDatabase
import com.example.moviedbapp.framework.data.local.RoomDataSource
import com.example.moviedbapp.framework.data.remote.RetrofitDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

private const val DATABASE_NAME = "themovies-db"

@Module
@InstallIn(SingletonComponent::class)
class FrameworkModule {

    @Provides
    @Singleton
    @Named("apiKey")
    fun apiKeyProvider(app: Application): String = app.getString(R.string.api_key)

    @Provides
    @Singleton
    fun databaseProvider(app: Application) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun peliculaLocalDataSource(database: AppDatabase): PeliculaLocalDataSource = RoomDataSource(database)

    @Provides
    fun peliculaRemoteDataSource(): PeliculaRemoteDataSource = RetrofitDataSource()
}