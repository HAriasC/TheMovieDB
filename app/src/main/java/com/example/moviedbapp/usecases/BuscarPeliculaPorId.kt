package com.example.moviedbapp.usecases

import androidx.lifecycle.asLiveData
import com.example.moviedbapp.data.repositories.PeliculasRepository
import com.example.moviedbapp.domain.Pelicula

class BuscarPeliculaPorId(private val peliculasRepository: PeliculasRepository) {
    suspend fun invoke(id: Int): Pelicula? = peliculasRepository.findById(id).asLiveData().value
}