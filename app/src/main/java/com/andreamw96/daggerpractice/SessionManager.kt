package com.andreamw96.daggerpractice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.andreamw96.daggerpractice.models.User
import com.andreamw96.daggerpractice.utils.logd
import com.andreamw96.daggerpractice.views.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {

    private var cachedUser : MediatorLiveData<AuthResource<User>> = MediatorLiveData()

    fun authenticateWithId(source: LiveData<AuthResource<User>>) {
        if(cachedUser != null) {
            cachedUser.value = AuthResource.loading(null)
            cachedUser.addSource(source, object : Observer<AuthResource<User>> {
                override fun onChanged(t: AuthResource<User>?) {
                    cachedUser.value = t
                    cachedUser.removeSource(source)
                }

            })
        } else {
            logd("authenticateWithId: previous session detected. Retrieving user from cache")
        }
    }

    fun logout() {
        logd("logout: Logging Out...")
        cachedUser.value = AuthResource.logout()
    }

    fun getAuthUser() : LiveData<AuthResource<User>> {
        return cachedUser
    }
}