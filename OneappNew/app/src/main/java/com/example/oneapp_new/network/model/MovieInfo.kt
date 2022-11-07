package com.example.oneapp_new.network.model

import com.google.gson.annotations.SerializedName


data class MoviesInfo(@SerializedName("page")val pageNumber:Int,@SerializedName("total_pages") val totalPages:Int,@SerializedName("total_results") val total_results:Int,
@SerializedName("results") val movies:MutableList<Movie>)