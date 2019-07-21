package com.andreamw96.daggerpractice.dagger.auth

import androidx.lifecycle.ViewModel
import com.andreamw96.daggerpractice.dagger.ViewModelKey
import com.andreamw96.daggerpractice.views.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(viewModel: AuthViewModel) : ViewModel
}