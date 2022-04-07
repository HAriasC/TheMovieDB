package com.example.moviedbapp.data.source

import com.example.moviedbapp.domain.Usuario

interface UsuarioLocalDataSource {

    suspend fun findById(id: Int): Usuario
    suspend fun getAll(): List<Usuario>
    suspend fun update(usuario: Usuario)
    suspend fun clear()
}