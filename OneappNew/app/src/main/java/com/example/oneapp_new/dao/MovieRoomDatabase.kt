package com.example.oneapp_new.dao

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.oneapp_new.network.model.Movie

@Database(entities = arrayOf(Movie::class), version = 1, exportSchema = false)
abstract class MovieRoomDatabase: RoomDatabase() {
    abstract fun getMovieDao() : MovieDao


    companion object {
        @Volatile
        private var INSTANCE: MovieRoomDatabase? = null
        fun getDatabase(context: Context): MovieRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieRoomDatabase::class.java,
                    "movie_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}