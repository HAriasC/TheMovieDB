package com.example.moviedbapp.framework.data.local

import com.example.moviedbapp.data.source.PeliculaLocalDataSource
import com.example.moviedbapp.domain.Pelicula
import com.example.moviedbapp.utils.toRoomPelicula
import com.example.moviedbapp.utils.toDomainPelicula
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class RoomDataSource(dataBase: AppDatabase): PeliculaLocalDataSource {

    private val peliculaDao = dataBase.peliculaDao()
    override fun findById(id: Int): Flow<Pelicula> {
        return peliculaDao.findById(id).map { it.toDomainPelicula() }
    }

    override fun getAll(): Flow<List<Pelicula>> {
        return peliculaDao.getAll().map { it.map { it.toDomainPelicula() } }
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

}