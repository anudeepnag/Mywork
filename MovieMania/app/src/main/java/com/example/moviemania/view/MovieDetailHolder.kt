package com.example.comcastexercise.comcastexec.views

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemania.R

class MovieDetailHolder (
    itemView: View

) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    override fun onClick(p0: View?) {

    }

    internal var charText: TextView
    internal var posterPath:ImageView
    internal var releaseDate:TextView
    init {
        charText = itemView.findViewById(R.id.movie_name_text)
        posterPath = itemView.findViewById(R.id.posterpath)
        releaseDate =itemView.findViewById(R.id.release_date)
        itemView.setOnClickListener(this)

    }
}
