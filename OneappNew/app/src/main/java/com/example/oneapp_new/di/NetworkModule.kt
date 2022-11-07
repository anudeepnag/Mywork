package com.example.oneapp_new.di

import com.example.oneapp_new.network.api.MovieService
import com.example.oneapp_new.network.repository.MovieRepository
import com.example.oneapp_new.network.retrofit.RetrofitBuilderClass
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
class NetworkModule {



    @Provides
    @Inject
    fun provideHttpBuilder() = OkHttpClient.Builder().apply {
        addNetworkInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        addInterceptor {
            val request = it.request()
            val url: HttpUrl =
                request.url.newBuilder()
                    .addQueryParameter("api_key", "4d260d6f9588fb3f3194e2deb750ccca")
                    .build()
            it.proceed(request.newBuilder().url(url).build())
        }}.build()

    @Provides
    @Inject
    fun provideMovieService(client: OkHttpClient) =  Retrofit.Builder()
        .baseUrl(RetrofitBuilderClass.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(MovieService::class.java)


}
