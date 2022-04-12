package com.example.moviedbapp.framework.ui.detail

import androidx.lifecycle.SavedStateHandle
import com.example.moviedbapp.data.repositories.PeliculasRepository
import com.example.moviedbapp.usecases.BuscarPeliculaPorId
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import java.lang.IllegalStateException
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class DetalleActivityModule {

    @Provides
    fun buscarPeliculasPorIdProvider(peliculasRepository: PeliculasRepository) = BuscarPeliculaPorId(peliculasRepository)

    @Provides
    @Named("peliculaId")
    fun peliculaIdProvider(stateHandle: SavedStateHandle): Int =
        stateHandle.get<Int>("peliculaId")
            ?: throw IllegalStateException("Movie Id not found in the state handle")
}