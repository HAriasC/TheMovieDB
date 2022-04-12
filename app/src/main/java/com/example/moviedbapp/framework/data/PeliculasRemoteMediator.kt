package com.example.moviedbapp.framework.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.moviedbapp.data.source.local.PeliculaLocalDataSource
import com.example.moviedbapp.data.source.remote.PeliculaRemoteDataSource
import com.example.moviedbapp.framework.data.local.AppDatabase
import com.example.moviedbapp.framework.data.local.model.Pelicula
import com.example.moviedbapp.framework.data.local.model.RemoteKeys
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PeliculasRemoteMediator(
    private val dataBase: AppDatabase,
    private val peliculaLocalDataSource: PeliculaLocalDataSource,
    private val peliculaRemoteDataSource: PeliculaRemoteDataSource
) : RemoteMediator<Int, Pelicula>() {

    companion object {
        const val DEFAULT_PAGE_INDEX = 1
    }
    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Pelicula>
    ): MediatorResult {
        val page = when(loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: DEFAULT_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                if (prevKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                if (nextKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                nextKey
            }
        }

        try {
            val apiResponse = peliculaRemoteDataSource.getFromApiRest(page = page)

            val endOfPaginationReached = apiResponse.isEmpty()
            dataBase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    peliculaLocalDataSource.clearRemoteKeys()
                    peliculaLocalDataSource.clear()
                }
                val prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = apiResponse.map {
                    RemoteKeys(id = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                peliculaLocalDataSource.insertRemoteKeys(keys)
                peliculaLocalDataSource.save(apiResponse)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Pelicula>
    ): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                peliculaLocalDataSource.remoteKeysPeliculaId(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Pelicula>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { pelicula ->
                peliculaLocalDataSource.remoteKeysPeliculaId(pelicula.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Pelicula>): RemoteKeys? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { pelicula ->
                peliculaLocalDataSource.remoteKeysPeliculaId(pelicula.id)
            }
    }
}