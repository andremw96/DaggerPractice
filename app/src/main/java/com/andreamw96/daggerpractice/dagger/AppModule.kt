package com.andreamw96.daggerpractice.dagger

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.andreamw96.daggerpractice.R
import com.andreamw96.daggerpractice.SessionManager
import com.andreamw96.daggerpractice.models.User
import com.andreamw96.daggerpractice.utils.BASE_URL
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

// all of the application modules dependices for project
// liek retrofit, glide, etc
// only one instance needed
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRequestOptions() : RequestOptions {
        return RequestOptions()
            .placeholder(R.drawable.white_background)
            .error(R.drawable.white_background)
    }

    @Singleton
    @Provides
    fun provideGlideInstance(application: Application, requestOptions: RequestOptions) : RequestManager {
        return Glide.with(application)
            .setDefaultRequestOptions(requestOptions)
    }

    @Singleton
    @Provides
    fun provideAppDrawable(application: Application) : Drawable? {
        return ContextCompat.getDrawable(application, R.drawable.logo)
    }

    @Singleton
    @Provides
    fun provideSessionManager() : SessionManager {
        return SessionManager()
    }

    @Singleton
    @Provides
    @Named("app_user")
    fun someUser() : User {
        return User()
    }
}