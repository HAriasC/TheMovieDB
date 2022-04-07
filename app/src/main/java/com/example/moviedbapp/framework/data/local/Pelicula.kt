package com.example.moviedbapp.framework.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pelicula")
data class Pelicula(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "backdrop_path") val backdrop_path: String,
    @ColumnInfo(name = "poster_path") val poster_path: String,
    @ColumnInfo(name = "release_date") val release_date: String,
    @ColumnInfo(name = "vote_average") val vote_average: Double,
)