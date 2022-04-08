package com.example.moviedbapp.framework.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.moviedbapp.data.source.local.PeliculaLocalDataSource
import com.example.moviedbapp.data.source.remote.PeliculaRemoteDataSource
import com.example.moviedbapp.framework.data.local.model.Pelicula

@OptIn(ExperimentalPagingApi::class)
class PeliculasRemoteMediator(
    private val peliculaLocalDataSource: PeliculaLocalDataSource,
    peliculaRemoteDataSource: PeliculaRemoteDataSource
) : RemoteMediator<Int, Pelicula>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Pelicula>
    ): MediatorResult {
        TODO("Not yet implemented")
    }
}