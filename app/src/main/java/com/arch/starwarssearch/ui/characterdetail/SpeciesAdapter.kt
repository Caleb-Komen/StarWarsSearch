package com.arch.starwarssearch.ui.characterdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arch.starwarssearch.databinding.ListItemSpecieBinding
import com.arch.starwarssearch.model.SpeciePresentation

class SpeciesAdapter: ListAdapter<SpeciePresentation, SpeciesAdapter.SpeciesViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesViewHolder {
        return SpeciesViewHolder(
            ListItemSpecieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SpeciesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class SpeciesViewHolder(private val binding: ListItemSpecieBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: SpeciePresentation){
            binding.apply {
                specie = item
                executePendingBindings()
            }
        }
    }
}

private val DIFF_UTIL = object : DiffUtil.ItemCallback<SpeciePresentation>(){
    override fun areItemsTheSame(
        oldItem: SpeciePresentation,
        newItem: SpeciePresentation
    ): Boolean {
        return oldItem.name == oldItem.name
    }

    override fun areContentsTheSame(
        oldItem: SpeciePresentation,
        newItem: SpeciePresentation
    ): Boolean {
        return oldItem == oldItem
    }
}