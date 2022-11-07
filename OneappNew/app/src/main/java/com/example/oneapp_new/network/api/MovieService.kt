package com.example.oneapp_new.network.api

import com.example.oneapp_new.network.model.Configuration
import com.example.oneapp_new.network.model.MoviesInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieService {

    @GET("/3/discover/movie")
    suspend fun getMovies(@Query("page") pageNumber:Int?): Response<MoviesInfo>

    @Headers("Cache-Control: public, max-stale=2419200") // 4 weeks
    @GET("/3/configuration")
    suspend fun getConfiguration():Response<Configuration>
}