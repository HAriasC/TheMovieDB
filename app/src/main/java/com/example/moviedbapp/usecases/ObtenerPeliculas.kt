package com.example.moviedbapp.usecases

import com.example.moviedbapp.data.repositories.PeliculasRepository
import com.example.moviedbapp.domain.Pelicula
import kotlinx.coroutines.flow.Flow

class ObtenerPeliculas(private val peliculasRepository: PeliculasRepository) {

    suspend fun invoke(): Flow<List<Pelicula>> = peliculasRepository.getPeliculas()
}