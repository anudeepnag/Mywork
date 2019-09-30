package com.example.moviemania.repository

import com.example.moviemania.model.Movie
import com.example.moviemania.supporting.MovieResponse
import io.reactivex.Observable

interface Repository {
    fun getMostPopularMovies(): Observable<MovieResponse>
    fun getMostPopularMovies(page: Int): Observable<MovieResponse>
    abstract fun getMoviePoster(movieId:Int): Observable<Movie>
}