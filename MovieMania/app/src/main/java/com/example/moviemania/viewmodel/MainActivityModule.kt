package com.example.moviemania.viewmodel

import android.app.Activity
import com.example.moviemania.view.MainActivity

import dagger.Module
import dagger.Provides
@Module
class MainActivityModule {
    @Provides
      fun provideActivity(activity: MainActivity): Activity {
        return activity
    }
}
