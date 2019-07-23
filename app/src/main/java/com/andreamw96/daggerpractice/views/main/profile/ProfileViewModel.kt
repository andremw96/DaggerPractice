package com.andreamw96.daggerpractice.views.main.profile

import androidx.lifecycle.ViewModel
import com.andreamw96.daggerpractice.utils.logd
import javax.inject.Inject

class ProfileViewModel @Inject constructor() : ViewModel() {

    init {
        logd("ProfileViewModel is ready")
    }

}