package com.example.moviedbapp.usecases

import com.example.moviedbapp.framework.data.remote.model.Result
import com.example.moviedbapp.data.repositories.LoginRepository
import com.example.moviedbapp.domain.Usuario
import kotlinx.coroutines.flow.Flow

class IniciarSesion(private val loginRepository: LoginRepository) {

    suspend fun invoke(usuario: String, password: String): Flow<Result<Usuario>> = loginRepository.login(usuario, password)
}