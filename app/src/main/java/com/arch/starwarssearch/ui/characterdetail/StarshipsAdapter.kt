package com.arch.starwarssearch.ui.characterdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arch.starwarssearch.databinding.ListItemStarshipBinding
import com.arch.starwarssearch.model.StarshipPresentation

class StarshipsAdapter: ListAdapter<StarshipPresentation, StarshipsAdapter.StarshipsViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarshipsViewHolder {
        return StarshipsViewHolder(
            ListItemStarshipBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StarshipsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class StarshipsViewHolder(
        private val binding: ListItemStarshipBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(item: StarshipPresentation){
            binding.apply {
                starship = item
                executePendingBindings()
            }
        }
    }
}

private val DIFF_UTIL = object : DiffUtil.ItemCallback<StarshipPresentation>(){
    override fun areItemsTheSame(
        oldItem: StarshipPresentation,
        newItem: StarshipPresentation
    ) = oldItem.name == newItem.name

    override fun areContentsTheSame(
        oldItem: StarshipPresentation,
        newItem: StarshipPresentation
    ) = oldItem == newItem
}