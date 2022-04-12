package com.example.moviedbapp.usecases

import com.example.moviedbapp.data.repositories.LoginRepository
import com.example.moviedbapp.domain.Usuario
import com.example.moviedbapp.framework.data.remote.model.Result
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
class IniciarSesionTest {

    @Mock
    lateinit var loginRepository: LoginRepository

    lateinit var iniciarSesion: IniciarSesion

    @Before
    fun setUp() {
        iniciarSesion = IniciarSesion(loginRepository)
    }

    @Test
    fun `iniciar sesion de forma incorrecta`() {
        runBlocking {
            val mensajeError = flow {
                emit(Result.Error(Exception("Usuario Incorrecto")))
            }
            whenever(loginRepository.login("987654321", "111111")).thenReturn(mensajeError)
            val result = iniciarSesion.invoke("987654321", "111111")

            assertEquals(mensajeError, result)
        }
    }

    @Test
    fun `iniciar sesion de forma correcta`() {
        runBlocking {
            val usuario = flow {
                emit(Result.Success(Usuario(1, "Juan", "Perez", "juan@email.com")))
            }
            whenever(loginRepository.login("Admin", "Password*123")).thenReturn(usuario)
            val result = iniciarSesion.invoke("Admin", "Password*123")

            assertEquals( usuario, result)
        }
    }
}