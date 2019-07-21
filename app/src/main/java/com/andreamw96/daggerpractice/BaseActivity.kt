package com.andreamw96.daggerpractice

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.andreamw96.daggerpractice.models.User
import com.andreamw96.daggerpractice.utils.logd
import com.andreamw96.daggerpractice.views.auth.AuthActivity
import com.andreamw96.daggerpractice.views.auth.AuthResource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        subscribeObserver()
    }

    private fun subscribeObserver(){
        sessionManager.getAuthUser().observe(this, Observer<AuthResource<User>> { userAuthResource ->
            if(userAuthResource != null) {
                when(userAuthResource.status) {
                    AuthResource.AuthStatus.LOADING -> {}
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        logd("onChanged: LOGIN SUCCESS ${userAuthResource.data?.email}")
                    }
                    AuthResource.AuthStatus.ERROR -> {
                        Log.e("BaseActivity", "onError: ${userAuthResource.message}")
                    }
                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> navLoginScreen()
                }
            }
        })
    }

    private fun navLoginScreen() {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }

}