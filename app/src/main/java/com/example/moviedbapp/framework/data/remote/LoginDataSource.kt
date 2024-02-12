package com.example.moviedbapp.framework.data.remote

import com.example.moviedbapp.domain.Usuario
import com.example.moviedbapp.framework.data.remote.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.Exception

class LoginDataSource @Inject constructor() {

    suspend fun login(username: String, password: String): Flow<Result<Usuario>> {
        return flow {
            val result = AuthRetrofitService.service.login(username, password)
            if (result.isSuccessful) {
                emit(Result.Success(result.body() as Usuario))
            } else {
                val mensaje = result.errorBody().toString()
                result.errorBody()?.close()
                emit(Result.Error(Exception(mensaje)))
            }
        }
    }

    suspend fun loginFake(username: String, password: String): Flow<Result<Usuario>> = flow {
        if (username == "Admin" && password == "Password*123") {
            emit(Result.Success<Usuario>(Usuario(1, "Admin", "", "admin@email.com")))
        } else {
            emit(Result.Error(Exception("Usuario incorrecto.")))
        }
    }

    fun logout() {

    }
}