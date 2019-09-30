package com.example.moviemania

import android.app.Activity
import android.app.Application
import com.example.moviemania.component.DaggerMovieComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class ApplicationModule: Application(), HasActivityInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        val component = DaggerMovieComponent.builder().application(this).build()
        component.inject(this)


    }
    override fun activityInjector(): AndroidInjector<Activity> = androidInjector

}