package com.andreamw96.daggerpractice.dagger.auth

import com.andreamw96.daggerpractice.models.User
import com.andreamw96.daggerpractice.network.auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class AuthModule {

    @AuthScope
    @Provides
    @Named("auth_user")
    fun someUser(): User {
        return User()
    }

    @AuthScope
    @Provides
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }
}