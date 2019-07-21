package com.andreamw96.daggerpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.andreamw96.daggerpractice.utils.logd
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    @Inject
    private lateinit var asdasdasdasdjasd: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        logd(asdasdasdasdjasd)
    }
}
