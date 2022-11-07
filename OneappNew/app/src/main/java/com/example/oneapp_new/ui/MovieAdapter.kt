package com.example.oneapp_new.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.oneapp_new.R
import com.example.oneapp_new.network.model.Movie

class MovieAdapter(val context: Context,var movieList: MutableList<Movie> = mutableListOf(),var imageUrl:String = "") : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieText:TextView
        val movieImage:ImageView
        init {

            movieText = view.findViewById(R.id.tv_movie)
            movieImage = view.findViewById(R.id.iv_movie)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_movie_list, parent,false)
        return MovieViewHolder(view);
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        movie?.let {
            holder.movieText.text = it.title
            val options = RequestOptions()
            options.centerCrop()

            it.posterPath?.let {
               Glide.with(context).apply { options }.load(imageUrl+"w300"+it).into(holder.movieImage)
            }
        }

    }

    fun updateList(list: MutableList<Movie>,url:String) {
        this.movieList = list
        this.imageUrl = url
    }

    override fun getItemCount() = movieList.size
}