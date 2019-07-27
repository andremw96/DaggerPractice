package com.andreamw96.daggerpractice.dagger.main.posts

import com.andreamw96.daggerpractice.network.main.MainApi
import com.andreamw96.daggerpractice.views.main.post.PostsRecyclerAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class PostModule {

    @PostsScope
    @Provides
    fun provideMainApi(retrofit: Retrofit) : MainApi {
        return retrofit.create(MainApi::class.java)
    }

    @PostsScope
    @Provides
    fun provideAdapter() : PostsRecyclerAdapter {
        return PostsRecyclerAdapter()
    }

}