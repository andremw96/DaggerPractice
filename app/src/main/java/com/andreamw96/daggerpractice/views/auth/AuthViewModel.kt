package com.andreamw96.daggerpractice.views.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import com.andreamw96.daggerpractice.network.auth.AuthApi
import javax.inject.Inject

class AuthViewModel @Inject constructor(authApi: AuthApi) : ViewModel() {

    init {
        Log.d("AuthViewModel", "Auth View Model is working")

        if (authApi == null) {
            Log.d("AuthViewModel","auth api is NULL")
        } else {
            Log.d("AuthViewModel","auth api is NOT NULL")
        }
    }

}