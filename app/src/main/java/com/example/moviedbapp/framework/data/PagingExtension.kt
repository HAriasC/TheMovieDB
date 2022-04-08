package com.example.moviedbapp.framework.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.moviedbapp.data.repositories.PeliculasRepository
import com.example.moviedbapp.framework.data.local.model.Pelicula
import kotlinx.coroutines.flow.Flow


const val NETWORK_PAGE_SIZE = 20

fun PeliculasRepository.getPeliculasPaginated(): Flow<PagingData<Pelicula>> {

    val pagingSourceFactory = {
        getLocalSource().getAllPaginated()
    }
    @OptIn(ExperimentalPagingApi::class)
    return Pager(
        config = PagingConfig(NETWORK_PAGE_SIZE, enablePlaceholders = false),
        remoteMediator = PeliculasRemoteMediator(
            getLocalSource(),
            getRomoteSource()
        ),
        pagingSourceFactory = pagingSourceFactory).flow
}