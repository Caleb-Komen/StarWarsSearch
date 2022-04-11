package com.arch.starwarssearch.ui.characterdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arch.starwarssearch.databinding.ListItemFilmBinding
import com.arch.starwarssearch.model.FilmPresentation

class FilmsAdapter: ListAdapter<FilmPresentation, FilmsAdapter.FilmsViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        return FilmsViewHolder(
            ListItemFilmBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class FilmsViewHolder(private val binding: ListItemFilmBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: FilmPresentation){
            binding.apply {
                film = item
                executePendingBindings()
            }
        }
    }
}

private val DIFF_UTIL = object : DiffUtil.ItemCallback<FilmPresentation>(){
    override fun areItemsTheSame(oldItem: FilmPresentation, newItem: FilmPresentation): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: FilmPresentation, newItem: FilmPresentation): Boolean {
        return oldItem == newItem
    }
}