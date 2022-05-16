package com.arch.starwarssearch.ui.characterdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.arch.starwarssearch.R
import com.arch.starwarssearch.databinding.FragmentStarshipsBinding
import com.arch.starwarssearch.model.CharacterWithDetailsPresentation
import com.arch.starwarssearch.model.StarshipPresentation
import com.arch.starwarssearch.util.Result
import com.arch.starwarssearch.util.Result.*

class StarshipsFragment : Fragment() {
    private var _binding: FragmentStarshipsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CharacterDetailViewModel by activityViewModels()

    private val starshipsAdapter = StarshipsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStarshipsBinding.inflate(inflater, container, false).apply {
            rvStarships.adapter = starshipsAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.starships.observe(viewLifecycleOwner){
            val result = it ?: return@observe
            bindStarships(result)
        }
        viewModel.character.observe(viewLifecycleOwner){
            val result = it ?: return@observe
            val starships = getStarships(result)
            binding.starships = starships
        }
    }

    private fun getStarships(result: Result<CharacterWithDetailsPresentation>): List<StarshipPresentation>? {
        when (result){
            is Success -> return result.data?.starships!!
            is Error -> return null
            Loading -> {
                // no op
            }
        }
        return null
    }

    private fun bindStarships(result: Result<List<StarshipPresentation>>) {
        when (result){
            is Success -> {
                binding.starshipsProgressBar.visibility = View.GONE
                binding.starshipsErrorLayout.visibility = View.GONE
                binding.starshipsSuccessLayout.visibility = View.VISIBLE
                binding.starships = result.data
            }
            is Error -> {
                binding.starshipsProgressBar.visibility = View.GONE
                binding.starshipsErrorLayout.visibility = View.VISIBLE
                binding.starshipsSuccessLayout.visibility = View.GONE
                binding.tvStarshipsError.text = result.message
            }
            is Loading -> binding.starshipsProgressBar.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}