package com.example.oneapp_new.network.retrofit


import com.example.oneapp_new.network.api.MovieService
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilderClass {


     val retrofitClient = Retrofit.Builder()
          .baseUrl(BASE_URL)
          .client(OkHttpClient.Builder().apply {
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
               }
          }.build())
          .addConverterFactory(GsonConverterFactory.create())
          .build()
     val retrofitService = retrofitClient.create(MovieService::class.java)

     companion object {
          const val BASE_URL = "https://api.themoviedb.org/"
          lateinit var builder:RetrofitBuilderClass
         fun getInstance():RetrofitBuilderClass {
              if (!::builder.isInitialized) {
                   builder = RetrofitBuilderClass()
              }
              return builder
         }
     }

}