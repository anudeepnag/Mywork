package com.example.oneapp_new.di

import android.app.Application
import com.example.oneapp_new.MainActivity
import com.example.oneapp_new.network.repository.MovieRepository
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule


@Component(
    modules =[AndroidInjectionModule::class,
        MovieDaoModule::class,
    NetworkModule::class,
    ViewModelModule::class])
interface AppComponent {


    fun getMovieRepository(movieRepository: MovieRepository) : MovieRepository

    fun inject(activity: MainActivity)
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application) : AppComponent
    }

}