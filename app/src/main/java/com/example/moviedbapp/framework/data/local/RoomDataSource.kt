package com.example.moviedbapp.framework.data.local

import androidx.paging.PagingSource
import com.example.moviedbapp.data.source.local.PeliculaLocalDataSource
import com.example.moviedbapp.domain.Pelicula
import com.example.moviedbapp.framework.data.local.model.RemoteKeys
import com.example.moviedbapp.framework.data.local.model.Pelicula as PeliculaDao
import com.example.moviedbapp.utils.toRoomPelicula
import com.example.moviedbapp.utils.toDomainPelicula
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class RoomDataSource(dataBase: AppDatabase): PeliculaLocalDataSource {

    private val peliculaDao = dataBase.getPeliculaDao()
    private val remoteKeysDao = dataBase.getRemoteKeysDao()


    override fun findById(id: Int): Flow<Pelicula> {
        return peliculaDao.findById(id).map { it.toDomainPelicula() }
    }

    override fun getAll(): Flow<List<Pelicula>> {
        return peliculaDao.getAll().map { it -> it.map { it.toDomainPelicula() } }
    }

    override fun getAllPaginated(): PagingSource<Int, PeliculaDao> {
        return peliculaDao.getAllPaginated()
    }

    override suspend fun update(pelicula: Pelicula) {
        peliculaDao.update(pelicula.toRoomPelicula())
    }

    override suspend fun save(peliculas: List<Pelicula>) {
        peliculaDao.insertAll(peliculas.map { it.toRoomPelicula() })
    }

    override suspend fun delete(pelicula: Pelicula) {
        peliculaDao.delete(pelicula.toRoomPelicula())
    }

    override suspend fun clear() {
        peliculaDao.delete()
    }

    override suspend fun isEmpty(): Boolean = withContext(Dispatchers.Default){
        peliculaDao.size() <= 0
    }

    override suspend fun insertRemoteKeys(remoteKeys: List<RemoteKeys>) {
        return remoteKeysDao.insertAll(remoteKeys)
    }

    override suspend fun remoteKeysPeliculaId(id: Int): RemoteKeys? {
        return remoteKeysDao.remoteKeysPeliculaId(id)
    }

    override suspend fun clearRemoteKeys() {
        remoteKeysDao.clearRemoteKeys()
    }

}