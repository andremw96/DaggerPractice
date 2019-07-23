package com.andreamw96.daggerpractice.dagger.main

import com.andreamw96.daggerpractice.views.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributesProfileFragment() : ProfileFragment
}