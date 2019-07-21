package com.andreamw96.daggerpractice.utils

import android.app.Activity
import android.util.Log
import com.andreamw96.daggerpractice.BuildConfig

// Debug Log
fun Activity.logd(message: String) {
    if(BuildConfig.DEBUG) Log.d(this::class.java.simpleName, message)
}
