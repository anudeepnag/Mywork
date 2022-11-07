package com.example.oneapp_new.di


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.oneapp_new.viewmodel.MovieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: MovieViewModelFactory):MovieViewModelFactory

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun postListViewModel(viewModel: MovieViewModel): ViewModel


}



