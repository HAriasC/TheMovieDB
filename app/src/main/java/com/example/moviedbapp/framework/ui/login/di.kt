package com.example.moviedbapp.framework.ui.login

import com.example.moviedbapp.data.repositories.LoginRepository
import com.example.moviedbapp.usecases.IniciarSesion
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class LoginActivityModule {

    @Provides
    @ViewModelScoped
    fun login(loginRepository: LoginRepository) = IniciarSesion(loginRepository)
}