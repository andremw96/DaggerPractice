package com.andreamw96.daggerpractice.views.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.daggerpractice.models.User
import com.andreamw96.daggerpractice.network.auth.AuthApi
import com.andreamw96.daggerpractice.utils.logd
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class AuthViewModel @Inject constructor(var authApi: AuthApi) : ViewModel() {

    private var authUser: MediatorLiveData<AuthResource<User>> = MediatorLiveData()

    init {
        logd("Auth View Model is working")
    }

    fun authenticateWithId(userId: Int) {
        // tell the UI that request attempting to be made
        authUser.value = AuthResource.loading(null)

        // convert flowable to live data object
        val source: LiveData<AuthResource<User>> = LiveDataReactiveStreams.fromPublisher(
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

        authUser.addSource(source) { t ->
            authUser.value = t
            authUser.removeSource(source)
        }
    }

    fun observeUser(): LiveData<AuthResource<User>> {
        return authUser
    }

}