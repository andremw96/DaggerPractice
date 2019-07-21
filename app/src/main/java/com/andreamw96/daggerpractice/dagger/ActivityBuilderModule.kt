package com.andreamw96.daggerpractice.dagger

import com.andreamw96.daggerpractice.AuthActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    // make authActivity, is a potential client that can inject dependencies
    @ContributesAndroidInjector
    abstract fun contributeAuthActivity() : AuthActivity
}