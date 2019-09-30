package com.example.moviemania.model

data class MovieResults (
    val page: Int ?= null,
    val total_pages: Int ?= null,
    val movies : MutableList<Movie> ?= null

)