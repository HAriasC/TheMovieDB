package com.example.moviedbapp.framework.ui.main

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.map
import com.example.moviedbapp.domain.Pelicula
import com.example.moviedbapp.usecases.ObtenerPeliculas
import com.example.moviedbapp.usecases.ObtenerPeliculasPaginadas
import com.example.moviedbapp.utils.toDomainPelicula
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val obtenerPeliculas: ObtenerPeliculas,
    private val obtenerPeliculasPaginadas: ObtenerPeliculasPaginadas
) : ViewModel() {

    fun obtenerPeliculas(): LiveData<List<Pelicula>> = liveData {
        emitSource(obtenerPeliculas.invoke().flowOn(Dispatchers.IO).asLiveData())
    }

    fun obtenerPeliculasPaginadas(): Flow<PagingData<Pelicula>> {
        return obtenerPeliculasPaginadas.invoke().map {
            it.map { it.toDomainPelicula() }
        }
    }
}