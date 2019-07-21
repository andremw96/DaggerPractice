package com.andreamw96.daggerpractice.dagger

import android.app.Application
import dagger.Module
import dagger.Provides

// all of the application modules dependices for project
// liek retrofit, glide, etc
// only one instance needed
@Module
class AppModule {

    @Provides
    fun someString() : String = "sadasdasd"

    // function to check wether the "application" is available or not
    // get the application value from bindInstance application in app component
    // return false is not null
    @Provides
    fun getApp(application: Application) : Boolean {
        return application == null
    }
}