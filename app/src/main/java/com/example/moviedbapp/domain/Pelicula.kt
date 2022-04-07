package com.example.moviedbapp.domain

data class Pelicula(
    val id: Int,
    val title: String,
    val overview: String,
    val backdrop_path: String,
    val poster_path: String,
    val release_date: String,
    val vote_average: Double
)