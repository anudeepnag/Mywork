package com.example.moviemania.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.moviemania.R
import com.example.moviemania.model.Movie
import com.example.moviemania.supporting.Constants
import com.example.moviemania.viewmodel.MoviesActivityViewModel
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.description_layout.*
import java.util.stream.Collectors
import javax.inject.Inject


class DescriptionFragment : Fragment() {
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var moviesActivityViewModel: MoviesActivityViewModel
    private var movieId: Int = -1
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "onCreateView")
        arguments?.let {
            if (it.containsKey(MainActivity.MOVIE_ID)) {

                movieId = it.getInt(MainActivity.MOVIE_ID)

            }
        }
        getDetailsOfMovieId(movieId)
        return inflater.inflate(R.layout.description_layout, container, false)
    }

    private fun handleReponse(s: Movie?) {
        title.setText(s?.original_title)
        s?.poster_path.let {
            Glide.with(context).load(Constants.IMAGE_URL + it).into(imageView)
        } ?: s?.backdrop_path.let {

            Glide.with(context).load(Constants.IMAGE_URL + it).into(imageView)
        } ?: run {
            Glide.with(context).load(R.drawable.movie_templ).into(imageView)
        }
        release_year.setText(s?.release_date)
        score.setText(s?.popularity.toString())
        overview.setText(s?.overview)
        runtime.setText(s?.runtime.toString())
        updateGeneterDetails(s);


    }

    private fun updateGeneterDetails(s: Movie?) {
        val s: String? =
            s?.genres?.stream()?.map { it -> it.name.toString() }?.collect(Collectors.joining(","))
        genere.setText(s.toString())
    }

    fun getDetailsOfMovieId(localMovieId: Int) {
        moviesActivityViewModel =
            ViewModelProviders.of(this, factory).get(MoviesActivityViewModel::class.java)
        moviesActivityViewModel.getMoviePoster(localMovieId).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { t: Throwable -> Log.e(TAG, "Error Message" + t.message) }
            .subscribe({ s -> handleReponse(s) }, { e -> handleError(e) })
    }

    private fun handleError(e: Throwable?) {
        Toast.makeText(context, getString(R.string.network_error), Toast.LENGTH_LONG).show()
    }
    companion object {
        val TAG:String? = DescriptionFragment::class.qualifiedName
    }
}

