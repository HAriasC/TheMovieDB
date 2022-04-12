package com.example.moviedbapp.framework.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbapp.databinding.ItemLoadingStateBinding

class PeliculasLoadStateAdapter(private val retryToFetchData: () -> Unit) : LoadStateAdapter<PeliculasLoadStateAdapter.LoadStateViewHolder>() {

    inner class LoadStateViewHolder(var binding: ItemLoadingStateBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.itemView.apply {
            holder.binding.buttonRetry.isVisible = loadState !is LoadState.Loading
            holder.binding.textViewError.isVisible = loadState !is LoadState.Loading
            holder.binding.progressbar.isVisible = loadState is LoadState.Loading

            if (loadState is LoadState.Error) {
                holder.binding.textViewError.text = loadState.error.localizedMessage
            }

            holder.binding.buttonRetry.setOnClickListener {
                retryToFetchData.invoke()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(ItemLoadingStateBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}