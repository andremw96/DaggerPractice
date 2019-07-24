package com.andreamw96.daggerpractice.views.main.post

import androidx.lifecycle.ViewModel
import com.andreamw96.daggerpractice.SessionManager
import com.andreamw96.daggerpractice.network.main.MainApi
import com.andreamw96.daggerpractice.utils.logd
import javax.inject.Inject

class PostsViewModel @Inject constructor(var sessionManager: SessionManager, var mainApi: MainApi): ViewModel() {

    init {
        logd("PostsViewModel is working")


    }

}