package com.arch.starwarssearch.ui.charactersearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arch.starwarssearch.databinding.ListItemCharacterBinding
import com.arch.starwarssearch.model.CharacterPresentation

class CharactersAdapter: ListAdapter<CharacterPresentation, CharactersAdapter.CharactersViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder(
            ListItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class CharactersViewHolder(
        private val binding: ListItemCharacterBinding
    ): RecyclerView.ViewHolder(binding.root){
        init {
            binding.setClickListener { view ->
                val action = CharacterSearchFragmentDirections
                    .actionCharacterSearchFragmentToCharacterDetailFragment(getItem(adapterPosition))
                view.findNavController().navigate(action)
            }
        }

        fun bind(item: CharacterPresentation){
            binding.apply {
                characterName = item.name
                executePendingBindings()
            }
        }
    }
}

val DIFF_UTIL = object : DiffUtil.ItemCallback<CharacterPresentation>(){
    override fun areItemsTheSame(oldItem: CharacterPresentation, newItem: CharacterPresentation): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: CharacterPresentation, newItem: CharacterPresentation): Boolean {
        return oldItem == newItem
    }
}