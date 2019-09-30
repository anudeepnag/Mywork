package com.example.moviemania.repository

import com.example.moviemania.model.Movie
import com.example.moviemania.supporting.Constants
import com.example.moviemania.supporting.MovieApiInterFace
import com.example.moviemania.supporting.MovieResponse
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val movieApiInterFace: MovieApiInterFace):Repository {
    override fun getMoviePoster(movieId: Int): Observable<Movie> {
        return movieApiInterFace.getMoviePoster(movieId,Constants.API_KEY)

    }

    override fun getMostPopularMovies(page: Int): Observable<MovieResponse> {
        return movieApiInterFace.getMostPopularMovies(Constants.QUERY,Constants.API_KEY, Locale.getDefault().language,page)
    }

    override fun getMostPopularMovies(): Observable<MovieResponse> {
        return movieApiInterFace.getMostPopularMovies(Constants.QUERY,Constants.API_KEY, Locale.getDefault().language,1)
    }

}