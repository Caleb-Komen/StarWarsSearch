package com.arch.starwarssearch.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arch.starwarssearch.R
import com.arch.starwarssearch.databinding.FragmentStarWarsBinding
import com.arch.starwarssearch.databinding.FragmentStarshipsBinding
import com.google.android.material.tabs.TabLayoutMediator

class StarWarsFragment : Fragment() {
    private var _binding: FragmentStarWarsBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_star_wars, container, false)
        _binding = FragmentStarWarsBinding.bind(view)
        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager
        viewPager.adapter = StarWarsPagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            val title = getTabTitle(position)
            tab.text = title
        }.attach()
        return binding.root
    }

    private fun getTabTitle(position: Int): String? {
        return when (position){
            CHARACTERS_PAGE_INDEX -> getString(R.string.characters)
            SEARCH_PAGE_INDEX -> getString(R.string.search)
            else -> null
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}