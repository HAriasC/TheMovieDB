package com.example.moviedbapp.data.source.remote

import com.example.moviedbapp.domain.Usuario

interface UsuarioRemoteDataSource {

    suspend fun getFromLogin(usuario: String, password: String): Usuario
}