package com.andreamw96.daggerpractice.dagger.main

import com.andreamw96.daggerpractice.dagger.main.posts.PostModule
import com.andreamw96.daggerpractice.dagger.main.posts.PostViewModelsModule
import com.andreamw96.daggerpractice.dagger.main.posts.PostsScope
import com.andreamw96.daggerpractice.dagger.main.profile.ProfileScope
import com.andreamw96.daggerpractice.dagger.main.profile.ProfileViewModelsModule
import com.andreamw96.daggerpractice.views.main.post.PostsFragment
import com.andreamw96.daggerpractice.views.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuilderModule {

    @ProfileScope
    @ContributesAndroidInjector(
        modules = [
            ProfileViewModelsModule::class
        ]
    )
    abstract fun contributesProfileFragment() : ProfileFragment

    @PostsScope
    @ContributesAndroidInjector (
        modules = [
            PostModule::class,
            PostViewModelsModule::class
        ]
    )
    abstract fun contributesPostsFragment() : PostsFragment
}