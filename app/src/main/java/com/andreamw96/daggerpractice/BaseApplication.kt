package com.andreamw96.daggerpractice

import com.andreamw96.daggerpractice.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

// base application class is the client  and appcomponent is the server
class BaseApplication : DaggerApplication() {

    // make AppComponent instantiate only once
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

}