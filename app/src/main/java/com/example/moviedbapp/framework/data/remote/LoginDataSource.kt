package com.example.moviedbapp.framework.data.remote

import com.example.moviedbapp.domain.Usuario
import com.example.moviedbapp.framework.data.remote.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
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

    fun logout() {
        // TODO: revoke authentication
    }
}