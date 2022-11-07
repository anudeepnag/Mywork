package com.example.oneapp_new

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.oneapp_new.dao.MovieApplication
import com.example.oneapp_new.dao.MovieRoomDatabase
import com.example.oneapp_new.di.MovieViewModelFactory
import com.example.oneapp_new.ui.MovieAdapter
import com.example.oneapp_new.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {






    private val viewModel: MovieViewModel by viewModels { viewModelFactory }


    private lateinit var movieAdapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        (this.applicationContext as MovieApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getConfiguration()
        movieAdapter = MovieAdapter(this)
        rv_movie.apply {
            adapter = movieAdapter
        }

        viewModel.configData.observe(this) { conf ->
            conf?.let {
                getMoviesList()
            }

        }
        MovieRoomDatabase.getDatabase(this).getMovieDao().getMovies().observe(this) {
            Log.d("Movie", "Movies Listning " + it.size)

            movieAdapter.updateList(it, viewModel.configData.value?.images?.baseUrl ?: "")
            movieAdapter.notifyDataSetChanged()
        }
    }

    private fun getMoviesList() {
        viewModel.getMovies(0)
    }
}