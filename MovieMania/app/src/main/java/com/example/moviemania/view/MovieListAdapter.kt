package com.example.comcastexercise.comcastexec.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviemania.R
import com.example.moviemania.model.Movie
import com.example.moviemania.supporting.Constants
import com.example.moviemania.supporting.ItemClickListent

class MovieListAdapter(
    var context: Context,
    private var data: MutableList<Movie>, private var clickListener: ItemClickListent
) : RecyclerView.Adapter<MovieDetailHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieDetailHolder {
        val spendVIew =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_content, parent, false)
        var holder = MovieDetailHolder(spendVIew)
        return holder
    }

    fun setListAdapter(data: MutableList<Movie>) {
        this.data = data;
    }

    override fun onBindViewHolder(holder: MovieDetailHolder, position: Int) {

        var text: String? = data[position].original_title

        data[position].poster_path.let {
            Glide.with(context).load(Constants.IMAGE_URL + it).into(holder.posterPath)
        } ?: data[position].backdrop_path.let {

            Glide.with(context).load(Constants.IMAGE_URL + it).into(holder.posterPath)
        } ?: run {
            Glide.with(context).load(R.drawable.movie_templ).into(holder.posterPath)
        }
        text.let { holder.charText.setText(text) }
        data[position].release_date.let {
            holder.releaseDate.setText(it)

        }
        holder.itemView.setTag(data[position].id)
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                clickListener.onItemClick(v?.getTag() as Int)
            }
        })
    }

    override fun getItemCount(): Int {
        return data.size
    }

}
