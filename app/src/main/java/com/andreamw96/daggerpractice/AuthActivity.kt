package com.andreamw96.daggerpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.andreamw96.daggerpractice.utils.logd
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var asdasdasdasdjasd: String

    var isAppNull : Boolean = false
        @Inject set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        logd(asdasdasdasdjasd)
        logd("is app null? $isAppNull")
    }
}
