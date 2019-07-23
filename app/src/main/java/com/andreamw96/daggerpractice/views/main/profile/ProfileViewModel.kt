package com.andreamw96.daggerpractice.views.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.daggerpractice.SessionManager
import com.andreamw96.daggerpractice.models.User
import com.andreamw96.daggerpractice.utils.logd
import com.andreamw96.daggerpractice.views.auth.AuthResource
import javax.inject.Inject

class ProfileViewModel @Inject constructor(var sessionManager: SessionManager) : ViewModel() {

    init {
        logd("ProfileViewModel is ready")
    }

    fun getAuthentitacedUser() : LiveData<AuthResource<User>> {
        return sessionManager.getAuthUser()
    }

}