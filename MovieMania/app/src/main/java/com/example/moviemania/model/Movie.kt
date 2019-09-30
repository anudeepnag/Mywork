package com.example.moviemania.model

data class Movie(
    val genres : MutableList<Genres>,
    val id:Int,
    val backdrop_path:String,
    val original_title : String,
    val overview : String,
    val popularity : Double,
    val poster_path : String,
    val release_date : String,
    val logo_path :String,
    val runtime : Int
    )