package com.andreamw96.daggerpractice.views.auth

import android.util.Log
import androidx.lifecycle.*
import com.andreamw96.daggerpractice.models.User
import com.andreamw96.daggerpractice.network.auth.AuthApi
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(var authApi: AuthApi) : ViewModel() {

    private var TAG = "AuthViewModel"

    private var authUser : MediatorLiveData<User> = MediatorLiveData()

    init {
        Log.d(TAG, "Auth View Model is working")
    }

    fun authenticateWithId(userId: Int) {
        // convert flowable to live data object
        val source : LiveData<User> = LiveDataReactiveStreams.fromPublisher(
            authApi.getUser(userId)
                .subscribeOn(Schedulers.io())
        )

        authUser.addSource(source, object : Observer<User>{
            override fun onChanged(t: User?) {
                authUser.value = t
                authUser.removeSource(source)
            }

        })
    }

    fun observeUser() : LiveData<User> {
        return authUser
    }

}