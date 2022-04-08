package com.example.moviedbapp.framework.data.remote

import com.example.moviedbapp.framework.data.remote.model.Pelicula
import com.google.gson.annotations.SerializedName

data class Response(
    @field:SerializedName("dates") val dates: Date,
    @field:SerializedName("page") val page: Int,
    @field:SerializedName("results") val results: List<Pelicula>,
    @field:SerializedName("total_pages") val total_pages: Int,
    @field:SerializedName("total_results") val total_results: Int
)

data class Date(
    @field:SerializedName("maximum") val maximum : String? = null,
    @field:SerializedName("minimum") val minimum : String? = null
)
