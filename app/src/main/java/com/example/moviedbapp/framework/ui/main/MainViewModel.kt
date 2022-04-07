package com.example.moviedbapp.framework.ui.main

import androidx.lifecycle.*
import com.example.moviedbapp.domain.Pelicula
import com.example.moviedbapp.usecases.ObtenerPeliculas
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val obtenerPeliculas: ObtenerPeliculas) : ViewModel() {



    fun obtenerPeliculas(): LiveData<List<Pelicula>> = liveData {
        emitSource(obtenerPeliculas.invoke().flowOn(Dispatchers.IO).asLiveData())
    }
}