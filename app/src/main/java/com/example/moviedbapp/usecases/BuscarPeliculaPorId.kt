package com.example.moviedbapp.usecases

import android.util.Log
import androidx.lifecycle.asLiveData
import com.example.moviedbapp.data.repositories.PeliculasRepository
import com.example.moviedbapp.domain.Pelicula
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

class BuscarPeliculaPorId(private val peliculasRepository: PeliculasRepository) {
    suspend fun invoke(id: Int): Flow<Pelicula?> {
        return peliculasRepository.findById(id)
    }
}