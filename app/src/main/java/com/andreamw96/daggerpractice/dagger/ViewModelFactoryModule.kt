package com.andreamw96.daggerpractice.dagger

import androidx.lifecycle.ViewModelProvider
import com.andreamw96.daggerpractice.viewmodels.ViewModelProvidersFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelProvidersFactory: ViewModelProvidersFactory) : ViewModelProvider.Factory

}