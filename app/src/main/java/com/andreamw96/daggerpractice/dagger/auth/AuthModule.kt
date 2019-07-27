package com.andreamw96.daggerpractice.dagger.auth

import com.andreamw96.daggerpractice.network.auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule {

    @AuthScope
    @Provides
    fun provideAuthApi(retrofit: Retrofit) : AuthApi {
        return retrofit.create(AuthApi::class.java)
    }
}