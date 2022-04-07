package com.example.moviedbapp.framework.data.remote

data class Result(
    val dates: Date,
    val page: Int,
    val results: List<Pelicula>,
    val total_pages: Int,
    val total_results: Int
)

data class Date(
    val maximum : String? = null,
    val minimum : String? = null
)
