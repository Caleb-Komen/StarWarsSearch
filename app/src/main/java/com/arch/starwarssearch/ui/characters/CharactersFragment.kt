package com.arch.starwarssearch.ui.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.arch.starwarssearch.R
import com.arch.starwarssearch.databinding.FragmentCharactersBinding
import com.arch.starwarssearch.model.CharacterPresentation
import com.arch.starwarssearch.util.Result
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {
    private var _binding: FragmentCharactersBinding? = null
    val binding get() = _binding!!

    val viewModel: CharactersViewModel by viewModels()

    val adapter = CharactersListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        binding.rvCharacters.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllCharacters().observe(viewLifecycleOwner){
            val result = it ?: return@observe
            filterResult(result)
        }
    }

    private fun filterResult(result: Result<List<CharacterPresentation>>) {
        when (result){
            is Result.Success -> {
                binding.characters = result.data!!
            }
            is Result.Error -> showSnackbar(result.message!!)
            is Result.Loading -> {
                // No op
            }
        }
    }


    private fun showSnackbar(message: String){
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}