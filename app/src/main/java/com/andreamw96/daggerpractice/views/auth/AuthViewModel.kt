package com.andreamw96.daggerpractice.views.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class AuthViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var string: String

    init {
        Log.d("AuthViewModel", "Auth View Model is working")
        Log.d("AuthViewModel", string)
    }

}