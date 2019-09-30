package com.example.moviemania.supporting

import com.example.moviemania.model.Movie

data class MovieResponse(
    val page : Integer,
    val total_pages : Integer,
    val total_results: Integer,
    val results : MutableList<Movie>


)