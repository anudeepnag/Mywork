package com.example.moviemania.component


import android.app.Application
import com.example.moviemania.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


/*
 * This componet will have all the Depended componets for
 * this application
 */
@Singleton
@Component(modules =  [AndroidSupportInjectionModule::class, AndroidInjectionModule::class,
    ComponentModule::class, ActivityComponentBuilder::class,FragmentComponentBuilder::class])
interface MovieComponent{
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): MovieComponent
    }

    fun inject(upcomingMoviesApplication: ApplicationModule)
}