package com.example.oneapp_new.dao

import android.app.Application
import com.example.oneapp_new.di.AppComponent
import com.example.oneapp_new.di.DaggerAppComponent
import dagger.android.DaggerApplication

class MovieApplication : Application() {
    val database by lazy { MovieRoomDatabase.getDatabase(this) }
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}