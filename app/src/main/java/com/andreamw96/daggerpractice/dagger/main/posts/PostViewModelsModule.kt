package com.andreamw96.daggerpractice.dagger.main.posts

import androidx.lifecycle.ViewModel
import com.andreamw96.daggerpractice.dagger.ViewModelKey
import com.andreamw96.daggerpractice.views.main.post.PostsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PostViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindPostsViewModel(viewModel: PostsViewModel) : ViewModel

}