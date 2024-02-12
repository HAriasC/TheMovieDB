package com.example.moviedbapp.data.repositories

import com.example.moviedbapp.framework.data.remote.LoginDataSource
import com.example.moviedbapp.framework.data.remote.model.Result
import com.example.moviedbapp.domain.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginRepository @Inject constructor(val dataSource: LoginDataSource) {
    var user: Usuario? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    suspend fun login(username: String, password: String): Flow<Result<Usuario>> {
        val result = dataSource.loginFake(username, password)
        return result.flowOn(Dispatchers.Default).conflate()
    }

    private fun setLoggedInUser(loggedInUser: Usuario) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}