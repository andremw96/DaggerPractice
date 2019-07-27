package com.andreamw96.daggerpractice.dagger

import com.andreamw96.daggerpractice.dagger.auth.AuthModule
import com.andreamw96.daggerpractice.dagger.auth.AuthScope
import com.andreamw96.daggerpractice.dagger.auth.AuthViewModelModule
import com.andreamw96.daggerpractice.dagger.main.MainFragmentBuilderModule
import com.andreamw96.daggerpractice.dagger.main.MainModule
import com.andreamw96.daggerpractice.dagger.main.MainScope
import com.andreamw96.daggerpractice.dagger.main.MainViewModelsModule
import com.andreamw96.daggerpractice.views.auth.AuthActivity
import com.andreamw96.daggerpractice.views.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    //contributeandroidinjcctor creates subcomponent

    // make authActivity, is a potential client that can inject dependencies
    @AuthScope
    @ContributesAndroidInjector(
        modules = [
            AuthViewModelModule::class,
            AuthModule::class
        ]
    )
    abstract fun contributeAuthActivity() : AuthActivity

    @MainScope
    @ContributesAndroidInjector (
        modules = [
            MainFragmentBuilderModule::class,
            MainViewModelsModule::class,
            MainModule::class
        ]
    )
    abstract fun contributeMainActivity() : MainActivity
}