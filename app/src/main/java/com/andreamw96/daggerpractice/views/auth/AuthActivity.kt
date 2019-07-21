package com.andreamw96.daggerpractice.views.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.andreamw96.daggerpractice.R
import com.andreamw96.daggerpractice.viewmodels.ViewModelProvidersFactory
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

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

        viewModel = ViewModelProviders.of(this, viewModelProvidersFactory).get(AuthViewModel::class.java)

        setLogo()
    }

    private fun setLogo() {
        requestManager
            .load(logo)
            .into(login_logo)
    }
}
