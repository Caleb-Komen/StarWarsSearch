package com.arch.starwarssearch.ui

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arch.starwarssearch.model.Character
import com.arch.starwarssearch.model.CharacterPresentation
import com.arch.starwarssearch.ui.charactersearch.CharactersAdapter

@BindingAdapter("items")
fun setCharacters(view: RecyclerView, items: List<CharacterPresentation>?){
    items?.let {
        val adapter = view.adapter as CharactersAdapter
        adapter.submitList(items)
    }
}