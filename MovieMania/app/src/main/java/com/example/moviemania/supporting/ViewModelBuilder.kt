package com.example.moviemania.supporting


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviemania.viewmodel.MoviesActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBuilder {
    @Binds
    abstract fun bindViewModelProviderFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
    @Binds
    @IntoMap
    @ViewModelKey(MoviesActivityViewModel::class)
    abstract fun bindMoviesViewModel(viewModel: MoviesActivityViewModel): ViewModel

}