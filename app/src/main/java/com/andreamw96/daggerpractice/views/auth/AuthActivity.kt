package com.andreamw96.daggerpractice.views.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.andreamw96.daggerpractice.R
import com.andreamw96.daggerpractice.models.User
import com.andreamw96.daggerpractice.utils.logd
import com.andreamw96.daggerpractice.viewmodels.ViewModelProvidersFactory
import com.andreamw96.daggerpractice.views.main.MainActivity
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject
import javax.inject.Named

class AuthActivity : DaggerAppCompatActivity(), View.OnClickListener {

    private lateinit var viewModel: AuthViewModel

    @Inject
    lateinit var viewModelProvidersFactory: ViewModelProvidersFactory

    @set:Inject
    var logo : Drawable? = null

    @Inject
    lateinit var requestManager: RequestManager


    @field: [Inject Named("app_user")]
    lateinit var userNumber1: User

    @field: [Inject Named("auth_user")]
    lateinit var userNumber2: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        setLogo()

        viewModel = ViewModelProviders.of(this, viewModelProvidersFactory).get(AuthViewModel::class.java)

        subscribeObservers()

        // print memory adres of usernumber1 & 2
        //userNumber1 is singleton from appComponent scope so it will be reused
        logd("onCreate $userNumber1")
        //userNumber2 is scoped by AuthScope so it will recreate whenever the authactivity recreate
        logd("onCreate $userNumber2")

        login_button.setOnClickListener(this)
    }

    private fun setLogo() {
        requestManager
            .load(logo)
            .into(login_logo)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.login_button -> attemptLogin()
        }
    }

    private fun attemptLogin() {
        if(TextUtils.isEmpty(user_id_input.text.toString())) {
            return
        }
        viewModel.authenticateWithId( (user_id_input.text.toString()).toInt() )
    }

    private fun onLoginSuccess() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun subscribeObservers() {
        viewModel.observeAuthState().observe(this, Observer<AuthResource<User>> { userAuthResource ->
            if(userAuthResource != null) {
                when(userAuthResource.status) {
                    AuthResource.AuthStatus.LOADING -> showProgressBar(true)
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        showProgressBar(false)
                        logd("onChanged: LOGIN SUCCESS ${userAuthResource.data?.email}")
                        onLoginSuccess()
                    }
                    AuthResource.AuthStatus.ERROR -> {
                        showProgressBar(false)
                        Toast.makeText(this, "${userAuthResource.message}" +
                                "\nDid you enter number between 1 to 10?", Toast.LENGTH_SHORT).show()
                    }
                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> showProgressBar(false)
                }
            }
        })
    }

    private fun showProgressBar(isVisible: Boolean) {
        if(isVisible) {
            progress_bar.visibility = View.VISIBLE
        } else {
            progress_bar.visibility = View.GONE
        }
    }
}
