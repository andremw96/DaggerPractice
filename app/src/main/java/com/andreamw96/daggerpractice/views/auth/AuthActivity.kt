package com.andreamw96.daggerpractice.views.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.andreamw96.daggerpractice.R
import com.andreamw96.daggerpractice.models.User
import com.andreamw96.daggerpractice.utils.logd
import com.andreamw96.daggerpractice.viewmodels.ViewModelProvidersFactory
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity(), View.OnClickListener {

    private lateinit var viewModel: AuthViewModel

    @Inject
    lateinit var viewModelProvidersFactory: ViewModelProvidersFactory

    @set:Inject
    var logo : Drawable? = null

    @Inject
    lateinit var requestManager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        setLogo()

        viewModel = ViewModelProviders.of(this, viewModelProvidersFactory).get(AuthViewModel::class.java)

        subscribeObservers()

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

    private fun subscribeObservers() {
        viewModel.observeUser().observe(this, Observer<AuthResource<User>> { userAuthResource ->
            if(userAuthResource != null) {
                when(userAuthResource.status) {
                    AuthResource.AuthStatus.LOADING -> showProgressBar(true)
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        showProgressBar(false)
                        logd("onChanged: LOGIN SUCCESS ${userAuthResource.data?.email}")
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
