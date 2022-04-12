package com.example.moviedbapp.framework.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbapp.domain.Pelicula
import com.example.moviedbapp.databinding.ItemPeliculaBinding
import com.example.moviedbapp.utils.loadUrl

class PeliculasPagingAdapter(private val listener: (Int, Pelicula) -> Unit) : PagingDataAdapter<Pelicula, PeliculasPagingAdapter.ViewHolder>(DIFF_CALLBACK) {

    class ViewHolder(val binding: ItemPeliculaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pelicula: Pelicula) {
            binding.apply {
                imageView.loadUrl("https://image.tmdb.org/t/p/w500/${pelicula.poster_path}")
                tNombre.text = pelicula.title
            }
        }
    }
    companion object {
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<Pelicula>(){
            override fun areItemsTheSame(oldItem: Pelicula, newItem: Pelicula): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Pelicula, newItem: Pelicula): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            getItem(position)?.let {
                holder.bind(it)
            }
            setOnClickListener {
                getItem(position)?.let { pelicula -> listener(position, pelicula) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemPeliculaBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}