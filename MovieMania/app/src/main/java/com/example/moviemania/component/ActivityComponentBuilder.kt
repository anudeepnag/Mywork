package com.example.moviemania.component
import com.example.moviemania.view.MainActivity
import com.example.moviemania.viewmodel.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


/*
 * Here we will declare the all the activirty componets
 * respective Acitivity Module
 */
@Module
abstract class ActivityComponentBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity
}

