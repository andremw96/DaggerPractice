package com.andreamw96.daggerpractice.dagger

import com.andreamw96.daggerpractice.dagger.auth.AuthModule
import com.andreamw96.daggerpractice.dagger.auth.AuthViewModelModule
import com.andreamw96.daggerpractice.dagger.main.MainFragmentBuilderModule
import com.andreamw96.daggerpractice.views.auth.AuthActivity
import com.andreamw96.daggerpractice.views.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    //contributeandroidinjcctor creates subcomponent

    // make authActivity, is a potential client that can inject dependencies
    @ContributesAndroidInjector(
        modules = [
            AuthViewModelModule::class,
            AuthModule::class
        ]
    )
    abstract fun contributeAuthActivity() : AuthActivity

    @ContributesAndroidInjector (
        modules = [
            MainFragmentBuilderModule::class
        ]
    )
    abstract fun contributeMainActivity() : MainActivity
}