package com.example.moviemania.network


import com.example.moviemania.supporting.Constants
import com.example.moviemania.supporting.MovieApiInterFace
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    internal fun provideRetrofitCreation(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }
    @Provides
    @Singleton
    fun movieApiInterFace(retrofit: Retrofit): MovieApiInterFace {
        return retrofit.create(MovieApiInterFace::class.java)
    }
}
