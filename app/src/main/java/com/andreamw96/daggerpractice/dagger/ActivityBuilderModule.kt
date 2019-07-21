package com.andreamw96.daggerpractice.dagger

import com.andreamw96.daggerpractice.dagger.auth.AuthModule
import com.andreamw96.daggerpractice.dagger.auth.AuthViewModelModule
import com.andreamw96.daggerpractice.views.auth.AuthActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    // make authActivity, is a potential client that can inject dependencies
    @ContributesAndroidInjector(
        modules = [
            AuthViewModelModule::class,
            AuthModule::class
        ]
    )
    abstract fun contributeAuthActivity() : AuthActivity
}