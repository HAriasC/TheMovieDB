package com.example.moviedbapp.framework.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.moviedbapp.databinding.ItemPeliculaBinding
import com.example.moviedbapp.domain.Pelicula
import com.example.moviedbapp.utils.loadUrl

class PeliculasAdapter(private val listener: (Int, Pelicula) -> Unit) :
    RecyclerView.Adapter<ViewHolder>() {

    private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<Pelicula>(){
        override fun areItemsTheSame(oldItem: Pelicula, newItem: Pelicula): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Pelicula, newItem: Pelicula): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return PeliculaViewHolder(
            ItemPeliculaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pelicula = differ.currentList[position]
        (holder as PeliculaViewHolder).bind(pelicula)
        holder.itemView.setOnClickListener{
            listener(position, pelicula)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Pelicula>){
        differ.submitList(list)
    }

    class PeliculaViewHolder(private val binding: ItemPeliculaBinding) : ViewHolder(binding.root) {
        fun bind(pelicula: Pelicula) {
            binding.apply {
                imageView.loadUrl("https://image.tmdb.org/t/p/w500/${pelicula.poster_path}")
                tNombre.text = pelicula.title
            }
        }
    }
}