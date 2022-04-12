package com.example.moviedbapp.framework.ui.detail

import androidx.lifecycle.*
import com.example.moviedbapp.domain.Pelicula
import com.example.moviedbapp.usecases.BuscarPeliculaPorId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DetalleViewModel @Inject constructor(
    @Named("peliculaId") private val peliculaId: Int,
    private val buscarPeliculaPorId: BuscarPeliculaPorId
) : ViewModel() {

    private val _model = MutableLiveData<Pelicula>()
    val model: LiveData<Pelicula>
        get() {
            if (_model.value == null) buscarPelicula()
            return _model
        }
    private fun buscarPelicula() = viewModelScope.launch {
        buscarPeliculaPorId.invoke(peliculaId).collect {
            _model.value = it
        }
    }
}