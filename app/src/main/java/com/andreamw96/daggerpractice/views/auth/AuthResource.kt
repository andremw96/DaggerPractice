package com.andreamw96.daggerpractice.views.auth

// A generic class that contains data and status about loading this data.
class AuthResource<T>(val status: AuthStatus, val data: T?, val message: String?) {

    enum class AuthStatus {
        AUTHENTICATED, ERROR, LOADING, NOT_AUTHENTICATED
    }

    companion object {
        fun <T> authenticated(data: T?): AuthResource<T> {
            return AuthResource(AuthStatus.AUTHENTICATED, data, null)
        }

        fun <T> error(msg: String, data: T?): AuthResource<T> {
            return AuthResource(AuthStatus.ERROR, data, msg)
        }

        fun <T> loading(data: T?): AuthResource<T> {
            return AuthResource(AuthStatus.LOADING, data, null)
        }

        fun <T> logout(): AuthResource<T> {
            return AuthResource(AuthStatus.NOT_AUTHENTICATED, null, null)
        }
    }

}