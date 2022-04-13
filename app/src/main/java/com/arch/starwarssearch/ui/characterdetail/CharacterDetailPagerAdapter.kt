package com.arch.starwarssearch.ui.characterdetail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

const val FILMS_PAGE_INDEX = 0
const val SPECIES_PAGE_INDEX = 1
const val STARSHIPS_PAGE_INDEX = 2
const val VEHICLES_PAGE_INDEX = 3

class CharacterDetailPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    private val tabFragmentCreators: Map<Int, () -> Fragment> = mapOf(
        FILMS_PAGE_INDEX to { FilmsFragment() },
        SPECIES_PAGE_INDEX to { SpeciesFragment() },
        STARSHIPS_PAGE_INDEX to { StarshipsFragment() },
        VEHICLES_PAGE_INDEX to { VehiclesFragment() }
    )
    override fun getItemCount() = tabFragmentCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}