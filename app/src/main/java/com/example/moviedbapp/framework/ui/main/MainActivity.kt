package com.example.moviedbapp.framework.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.moviedbapp.databinding.ActivityMainBinding
import com.example.moviedbapp.framework.ui.detail.DetalleActivity
import com.example.moviedbapp.framework.ui.main.adapters.PeliculasAdapter
import com.example.moviedbapp.framework.ui.main.adapters.PeliculasLoadStateAdapter
import com.example.moviedbapp.framework.ui.main.adapters.PeliculasPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PeliculasAdapter
    private lateinit var adapterPaging: PeliculasPagingAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = PeliculasAdapter(listener = { i, pelicula ->
            intent = Intent(this@MainActivity, DetalleActivity::class.java)
            startActivity(intent)
        })

        adapterPaging = PeliculasPagingAdapter(listener = { i, pelicula ->
            intent = Intent(this@MainActivity, DetalleActivity::class.java)
            intent.putExtra("peliculaId",pelicula.id)
            startActivity(intent)
        })

        binding.recycler.apply {
            adapter = adapterPaging.withLoadStateHeaderAndFooter(
                header = PeliculasLoadStateAdapter { adapterPaging.retry() },
                footer = PeliculasLoadStateAdapter { adapterPaging.retry() }
            )
            setHasFixedSize(true)
        }

        lifecycleScope.launch {
            viewModel.obtenerPeliculasPaginadas().collectLatest { peliculas ->
                adapterPaging.submitData(peliculas)
            }
        }
    }
}