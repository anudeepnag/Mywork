package com.example.oneapp_new.di

import android.app.Application
import com.example.oneapp_new.dao.MovieRoomDatabase
import dagger.Module
import dagger.Provides

@Module
class MovieDaoModule {

    @Provides
    fun getMovieDb(application: Application) = MovieRoomDatabase.getDatabase(application.applicationContext)

    @Provides
    fun getMovieDao(roomDatabase: MovieRoomDatabase)  = roomDatabase.getMovieDao()
}