package com.example.oneapp_new.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oneapp_new.dao.MovieDao
import com.example.oneapp_new.network.api.MovieService
import com.example.oneapp_new.network.model.Configuration
import com.example.oneapp_new.network.model.Movie
import com.example.oneapp_new.network.model.MoviesInfo
import com.example.oneapp_new.network.repository.MovieRepository
import com.example.oneapp_new.network.retrofit.RetrofitBuilderClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val movieDao: MovieDao,private val movieRepository: MovieRepository) : ViewModel() {

    private val mConfigData = MutableLiveData<Configuration?>()
    val configData:LiveData<Configuration?> = mConfigData

    private val mMovieInfo = MutableLiveData<MoviesInfo>()
    val movieInfo:LiveData<MoviesInfo> = mMovieInfo

    val movies:LiveData<MutableList<Movie>> = movieDao.getMovies()



    fun getConfiguration() {
        viewModelScope.launch(Dispatchers.Main) {
            val response = movieRepository.getConfiguration()
            if (response.isSuccessful) {
                mConfigData.postValue(response.body())
            } else {
            }
        }

    }

    fun getMovies(page:Int) {
        viewModelScope.launch(Dispatchers.Main) {
           movieRepository.getMovies()
        }
    }
}