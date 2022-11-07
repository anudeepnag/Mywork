package com.example.oneapp_new.network.repository

import com.example.oneapp_new.dao.MovieDao
import com.example.oneapp_new.network.api.MovieService
import com.example.oneapp_new.network.model.MoviesInfo
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(private val service: MovieService,private val movieDao: MovieDao) {

    suspend fun getConfiguration() = service.getConfiguration()

    suspend fun getMovies(page:Int? = null)  {
        val response = service.getMovies(page)
        if (response.isSuccessful) {
            movieDao.insertMovies(response.body()?.movies?: mutableListOf())
        }
    }

    suspend fun listenMovies() = movieDao.getMovies()
}