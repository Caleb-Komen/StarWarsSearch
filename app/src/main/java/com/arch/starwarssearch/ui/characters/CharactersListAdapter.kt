package com.arch.starwarssearch.ui.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arch.starwarssearch.databinding.CharacterItemBinding
import com.arch.starwarssearch.model.CharacterPresentation

class CharactersListAdapter: ListAdapter<CharacterPresentation, CharactersListAdapter.CharactersViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder(
            CharacterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }

    class CharactersViewHolder(
        private val binding: CharacterItemBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(item: CharacterPresentation){
            binding.apply {
                character = item
                executePendingBindings()
            }
        }
    }
}

private val DIFF_UTIL = object: DiffUtil.ItemCallback<CharacterPresentation>(){
    override fun areItemsTheSame(
        oldItem: CharacterPresentation,
        newItem: CharacterPresentation
    ): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(
        oldItem: CharacterPresentation,
        newItem: CharacterPresentation
    ): Boolean {
        return oldItem == newItem
    }
}