package com.andreamw96.daggerpractice.views.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.daggerpractice.SessionManager
import com.andreamw96.daggerpractice.models.User
import com.andreamw96.daggerpractice.network.auth.AuthApi
import com.andreamw96.daggerpractice.utils.logd
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class AuthViewModel @Inject constructor(var authApi: AuthApi, var sessionManager: SessionManager) : ViewModel() {

    init {
        logd("Auth View Model is working")
    }

    fun authenticateWithId(userId: Int) {
        logd("authenticateWithId: attempting to login")
        sessionManager.authenticateWithId(queryUserId(userId))
    }

    private fun queryUserId(userId: Int) : LiveData<AuthResource<User>> {
        return LiveDataReactiveStreams.fromPublisher(
            authApi.getUser(userId)
                .onErrorReturn {
                    val errorUser = User()
                    errorUser.id = -1
                    errorUser
                }
                .map{
                    if(it.id == -1) {
                        AuthResource.error("Could not authenticated", null)
                    }
                    AuthResource.authenticated(it)
                }
                .subscribeOn(Schedulers.io())
        )
    }

    fun observeAuthState(): LiveData<AuthResource<User>> {
        return sessionManager.getAuthUser()
    }

}