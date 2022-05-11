package com.arch.starwarssearch.ui

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arch.starwarssearch.R
import com.arch.starwarssearch.model.*
import com.arch.starwarssearch.ui.characterdetail.FilmsAdapter
import com.arch.starwarssearch.ui.characterdetail.SpeciesAdapter
import com.arch.starwarssearch.ui.characterdetail.StarshipsAdapter
import com.arch.starwarssearch.ui.characterdetail.VehiclesAdapter
import com.arch.starwarssearch.ui.characters.CharactersListAdapter
import com.arch.starwarssearch.ui.charactersearch.CharactersAdapter

@BindingAdapter("items")
fun setCharacters(view: RecyclerView, items: List<CharacterPresentation>?){
    items?.let {
        when (val adapter = view.adapter){
            is CharactersAdapter -> adapter.submitList(items)
            is CharactersListAdapter -> adapter.submitList(items)
        }
    }
}

@BindingAdapter("producerText")
fun setFilmProducer(view: TextView, item: String?){
    item?.let {
        val resource = view.context.resources
        val producer = resource.getString(R.string.producer_text, it)
        view.text = producer
    }
}

@BindingAdapter("items")
fun setSpecies(view: RecyclerView, items: List<SpeciePresentation>?){
    items?.let {
        val adapter = view.adapter as SpeciesAdapter
        adapter.submitList(items)
    }
}

@BindingAdapter("items")
fun setFilms(view: RecyclerView, items: List<FilmPresentation>?){
    items?.let {
        val adapter = view.adapter as FilmsAdapter
        adapter.submitList(items)
    }
}

@BindingAdapter("items")
fun setStarships(view: RecyclerView, items: List<StarshipPresentation>?){
    items?.let {
        val adapter = view.adapter as StarshipsAdapter
        adapter.submitList(items)
    }
}

@BindingAdapter("items")
fun setVehicles(view: RecyclerView, items: List<VehiclePresentation>?){
    items?.let {
        val adapter = view.adapter as VehiclesAdapter
        adapter.submitList(items)
    }
}
