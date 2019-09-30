package com.example.moviemania.supporting

import com.example.moviemania.model.Movie
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiInterFace {
@GET("search/movie")
fun getMostPopularMovies(
    @Query("query") query: String,
    @Query("api_key") apiKey: String,
    @Query("language") language: String,
    @Query("page") page: Int
): Observable<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMoviePoster(
        @Path("movie_id") movieId:Int,
        @Query("api_key") apiKey: String
        ): Observable<Movie>
}