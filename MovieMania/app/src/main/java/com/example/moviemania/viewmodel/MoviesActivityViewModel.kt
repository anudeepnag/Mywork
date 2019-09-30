package com.example.moviemania.viewmodel

import androidx.lifecycle.ViewModel
import com.example.moviemania.model.Movie
import com.example.moviemania.repository.MovieRepository
import com.example.moviemania.supporting.MovieResponse
import io.reactivex.Observable
import javax.inject.Inject

class MoviesActivityViewModel @Inject constructor(private  val  movieRepository: MovieRepository) : ViewModel() {
    private val mutableList : MutableList<Movie> = arrayListOf()
    fun getMostPopularMovies() : Observable<MovieResponse>  {
        mutableList.clear()
        return movieRepository.getMostPopularMovies()

    }
    fun getMostPopularMovies(page: Int): Observable<MovieResponse> {
         return movieRepository.getMostPopularMovies(page)
    }
    val addMovie: (movie :Movie)->Unit = {x->mutableList.add(x)}
    val getMovieList: MutableList<Movie> = mutableList

    fun getMoviePoster(movieId:Int) : Observable<Movie>  {
        return movieRepository.getMoviePoster(movieId)

    }
}
