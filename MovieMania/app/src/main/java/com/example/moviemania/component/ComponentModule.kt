package com.example.moviemania.component

import com.example.moviemania.network.NetworkModule
import com.example.moviemania.repository.Repository
import com.example.moviemania.supporting.ViewModelBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [NetworkModule::class, ViewModelBuilder::class])
class ComponentModule {
    @Provides
    @Singleton
    fun repository(repository: Repository): Repository = repository

}