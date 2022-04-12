package com.example.moviedbapp.framework.ui.detail

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.moviedbapp.R
import com.example.moviedbapp.databinding.ActivityDetalleBinding
import com.example.moviedbapp.utils.loadUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetalleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalleBinding
    private val viewModel: DetalleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.model.observe(this, Observer {
            binding.apply {
                toolbar.title = it.title+""
                imagen.loadUrl("https://image.tmdb.org/t/p/w780${it.backdrop_path}")
                findViewById<TextView>(R.id.tNota).text = "${it.vote_average}"
                findViewById<TextView>(R.id.tFecha).text = "${it.release_date}"
                findViewById<TextView>(R.id.tResumen).text = "${it.overview}"
            }
        })

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

    }
}