package com.andreamw96.daggerpractice.dagger.main.profile

import androidx.lifecycle.ViewModel
import com.andreamw96.daggerpractice.dagger.ViewModelKey
import com.andreamw96.daggerpractice.views.main.post.PostsViewModel
import com.andreamw96.daggerpractice.views.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ProfileViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel) : ViewModel

}