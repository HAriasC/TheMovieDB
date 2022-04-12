package com.example.moviedbapp.usecases

import androidx.paging.PagingData
import com.example.moviedbapp.data.repositories.PeliculasRepository
import com.example.moviedbapp.framework.data.local.model.Pelicula
import com.example.moviedbapp.framework.data.getPeliculasPaginated
import kotlinx.coroutines.flow.Flow

class ObtenerPeliculasPaginadas (private val peliculasRepository: PeliculasRepository) {

    fun invoke(): Flow<PagingData<Pelicula>> = peliculasRepository.getPeliculasPaginated()
}