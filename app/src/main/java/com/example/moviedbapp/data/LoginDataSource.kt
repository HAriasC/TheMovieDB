package com.example.moviedbapp.data

import android.util.Log
import com.example.moviedbapp.data.model.LoggedInUser
import com.example.moviedbapp.domain.Usuario
import java.io.IOException
import javax.inject.Inject

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource @Inject constructor() {

    fun login(username: String, password: String): Result<Usuario> {
        try {
            // TODO: handle loggedInUser authentication
            Log.e("LOG", java.util.UUID.randomUUID().toString())
            val fakeUser = Usuario(1, "Jane Doe", "", "")
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}