package com.example.moviemania.view

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.comcastexercise.comcastexec.views.MovieListAdapter
import com.example.moviemania.R
import com.example.moviemania.supporting.ItemClickListent
import com.example.moviemania.supporting.MovieResponse
import com.example.moviemania.viewmodel.MoviesActivityViewModel
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.movie_list_fragment.*
import javax.inject.Inject


class MovieListFragment() : Fragment() {
    private lateinit var itemClickListent: ItemClickListent;
    private var twoPaneSupport: Boolean = false;
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var movieListAdapter: MovieListAdapter;
    private lateinit var moviesActivityViewModel: MoviesActivityViewModel
    var progressDialog: ProgressDialog? = null

    override
    fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(MovieListFragment.TAG, "onCreateView")
        arguments?.let {
            if (it.containsKey(MainActivity.MULTI_PANE)) {
                twoPaneSupport = it.getBoolean(MainActivity.MULTI_PANE)
            }
        }

        return inflater.inflate(R.layout.movie_list_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()
        setupRecyclerView(item_list)
    }

    private fun setupRecyclerView(item_list: RecyclerView?) {
        movieListAdapter = context?.let {
            MovieListAdapter(
                it,
                moviesActivityViewModel.getMovieList,
                itemClickListent
            )
        }!!
        item_list?.apply { adapter = movieListAdapter }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        progressDialog = ProgressDialog(this.getActivity())
        progressDialog?.setMessage(getString(R.string.progress_movie_list))
        progressDialog?.setCancelable(false)
        progressDialog?.show()
        moviesActivityViewModel =
            ViewModelProviders.of(this, factory).get(MoviesActivityViewModel::class.java)
        moviesActivityViewModel.getMostPopularMovies().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnError { t: Throwable -> Log.e(TAG, "Error Message" + t.message) }
            .subscribe(
                { s -> handleReponse(s) },
                { e -> handlErrorRepsonse(e) })
    }

    private fun handlErrorRepsonse(e: Throwable?) {
        hideProgressBar()
        Toast.makeText(context, getString(R.string.network_error), Toast.LENGTH_LONG).show()
    }

    private fun handleReponse(movieResponse: MovieResponse) {
        updateMovieList(movieResponse)
        for (i in 1..(movieResponse.total_pages) as Int) {
            moviesActivityViewModel.getMostPopularMovies(i).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ s -> updateMovieList(s) },
                    { e -> handlErrorRepsonse(e) })
            hideProgressBar()

        }


    }

    private fun hideProgressBar() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog?.dismiss()
        }
    }

    fun setOnItemListner(itemClickListent: ItemClickListent) {
        this.itemClickListent = itemClickListent
    }

    private fun updateMovieList(movieResponse: MovieResponse) {
        movieResponse.results?.forEach {
            moviesActivityViewModel.addMovie(it)
            item_list.adapter?.notifyDataSetChanged()
        }
    }
    companion object {
        val TAG:String? = MovieListFragment::class.qualifiedName
    }
}

