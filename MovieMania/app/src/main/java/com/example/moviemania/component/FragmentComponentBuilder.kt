package com.example.moviemania.component

import com.example.moviemania.view.DescriptionFragment
import com.example.moviemania.view.MovieListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
/*
 * Here we will declare the all the Fragment componets
 *
 */
@Module
abstract class FragmentComponentBuilder {
    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MovieListFragment
    @ContributesAndroidInjector
    abstract fun contributeDescriptionFragment(): DescriptionFragment
}
