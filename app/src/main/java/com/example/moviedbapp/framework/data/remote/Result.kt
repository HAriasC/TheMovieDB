package com.example.moviedbapp.framework.data.remote

data class Result(
    val page: Int,
    val result: List<Pelicula>,
    val total_pages: Int,
    val total_results: Int
)
