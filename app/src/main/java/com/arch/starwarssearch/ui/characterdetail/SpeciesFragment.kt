package com.arch.starwarssearch.ui.characterdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.arch.starwarssearch.R
import com.arch.starwarssearch.databinding.FragmentSpeciesBinding
import com.arch.starwarssearch.model.SpeciePresentation
import com.arch.starwarssearch.util.Result
import com.arch.starwarssearch.util.Result.*

class SpeciesFragment : Fragment() {
    private var _binding: FragmentSpeciesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CharacterDetailViewModel by activityViewModels()

    private val speciesAdapter = SpeciesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSpeciesBinding.inflate(inflater, container, false).apply {
            rvSpecies.adapter = speciesAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.species.observe(viewLifecycleOwner){
            val result = it ?: return@observe
            bindSpecies(result)
        }
    }

    private fun bindSpecies(result: Result<List<SpeciePresentation>>) {
        when (result){
            is Success -> {
                binding.speciesProgressBar.visibility = View.GONE
                binding.speciesErrorLayout.visibility = View.GONE
                binding.speciesSuccessLayout.visibility = View.VISIBLE
                binding.species = result.data
            }
            is Error -> {
                binding.speciesProgressBar.visibility = View.GONE
                binding.speciesErrorLayout.visibility = View.VISIBLE
                binding.speciesSuccessLayout.visibility = View.GONE
                binding.tvSpeciesError.text = result.message
            }
            is Loading -> binding.speciesProgressBar.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}