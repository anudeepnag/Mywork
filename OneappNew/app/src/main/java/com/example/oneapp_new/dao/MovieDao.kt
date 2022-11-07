package com.example.oneapp_new.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.oneapp_new.network.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table")
    fun getMovies(): LiveData<MutableList<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Query("DELETE FROM movie_table")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(songs: MutableList<Movie>)

}