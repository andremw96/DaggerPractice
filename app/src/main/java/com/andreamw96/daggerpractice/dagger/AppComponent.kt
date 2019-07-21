package com.andreamw96.daggerpractice.dagger

import android.app.Application
import com.andreamw96.daggerpractice.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        AppModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder{

        //binding application instance to the application component
        @BindsInstance
        fun application(application: Application) : Builder

        fun build() : AppComponent
    }
}