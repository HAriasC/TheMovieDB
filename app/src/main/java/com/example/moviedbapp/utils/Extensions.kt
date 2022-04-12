package com.example.moviedbapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

const val API_KEY = "9af907de7aa7814a33a6a8a77c6939ab"
fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).into(this)
}