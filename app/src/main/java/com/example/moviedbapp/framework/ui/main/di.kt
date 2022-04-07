package com.example.moviedbapp.framework.ui.main

import com.example.moviedbapp.data.repositories.PeliculasRepository
import com.example.moviedbapp.usecases.ObtenerPeliculas
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class MainActivityModule {

    @Provides
    @ViewModelScoped
    fun getPeliculasProvider(peliculasRepository: PeliculasRepository) = ObtenerPeliculas(peliculasRepository)
}