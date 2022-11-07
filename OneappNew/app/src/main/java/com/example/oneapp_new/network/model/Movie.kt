package com.example.oneapp_new.network.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_table")
data class Movie(@SerializedName("title")
                 @ColumnInfo(name = "title")
                 val title: String = "",
                 @SerializedName("poster_path")
                 @ColumnInfo(name = "posterPath")
                 val posterPath:String,
                 @ColumnInfo(name = "id")
                 @PrimaryKey
                 @SerializedName("id")
                 var id: Int = 0)