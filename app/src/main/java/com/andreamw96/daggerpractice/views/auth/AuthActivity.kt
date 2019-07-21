package com.andreamw96.daggerpractice.views.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.andreamw96.daggerpractice.R
import com.andreamw96.daggerpractice.models.User
import com.andreamw96.daggerpractice.viewmodels.ViewModelProvidersFactory
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity(), View.OnClickListener {

    private val TAG = "AuthActivity"

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
        viewModel.observeUser().observe(this, object : Observer<User> {
            override fun onChanged(t: User?) {
                if (t != null) {
                    Log.d(TAG, "onChanged: ${t.email}")
                }
            }

        })
    }
}
