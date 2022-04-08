package com.example.moviedbapp.framework.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.moviedbapp.databinding.ActivityMainBinding
import com.example.moviedbapp.framework.ui.detail.DetalleActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PeliculasAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = PeliculasAdapter(listener = { i, pelicula ->
            intent = Intent(this@MainActivity, DetalleActivity::class.java)
            startActivity(intent)
        })

        binding.recycler.adapter = adapter

        viewModel.obtenerPeliculas().observe(this@MainActivity, Observer {
            adapter.submitList(it)
        })
    }
}