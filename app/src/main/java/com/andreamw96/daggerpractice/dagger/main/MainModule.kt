package com.andreamw96.daggerpractice.dagger.main

import com.andreamw96.daggerpractice.network.main.MainApi
import com.andreamw96.daggerpractice.views.main.post.PostsRecyclerAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @Provides
    fun provideMainApi(retrofit: Retrofit) : MainApi {
        return retrofit.create(MainApi::class.java)
    }

    @Provides
    fun provideAdapter() : PostsRecyclerAdapter {
        return PostsRecyclerAdapter()
    }

}