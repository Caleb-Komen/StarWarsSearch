package com.arch.starwarssearch.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.arch.starwarssearch.ui.characters.CharactersFragment
import com.arch.starwarssearch.ui.charactersearch.CharacterSearchFragment

const val CHARACTERS_PAGE_INDEX = 0
const val SEARCH_PAGE_INDEX = 1

class StarWarsPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    private val tabFragmentCreators: Map<Int, () -> Fragment> = mapOf(
        CHARACTERS_PAGE_INDEX to { CharactersFragment() },
        SEARCH_PAGE_INDEX to { CharacterSearchFragment() }
    )

    override fun getItemCount(): Int {
        return tabFragmentCreators.size
    }

    override fun createFragment(position: Int): Fragment {
        return tabFragmentCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}