package com.arch.starwarssearch.ui.characterdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.arch.starwarssearch.R
import com.arch.starwarssearch.databinding.FragmentFilmsBinding
import com.arch.starwarssearch.model.CharacterWithDetailsPresentation
import com.arch.starwarssearch.model.FilmPresentation
import com.arch.starwarssearch.util.Result
import com.arch.starwarssearch.util.Result.*

class FilmsFragment : Fragment() {
    private var _binding: FragmentFilmsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CharacterDetailViewModel by activityViewModels()

    private val filmsAdapter = FilmsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilmsBinding.inflate(inflater, container, false).apply {
            rvFilms.adapter = filmsAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.films.observe(viewLifecycleOwner){
            val result = it ?: return@observe
            bindFilms(result)
        }
        viewModel.character.observe(viewLifecycleOwner){
            val result = it ?: return@observe
            val films = getFilms(result)
            binding.films = films
        }
    }

    private fun getFilms(result: Result<CharacterWithDetailsPresentation>): List<FilmPresentation>? {
        when (result){
            is Success -> return result.data?.films!!
            is Error -> return null
            Loading -> {
                // no op
            }
        }
        return null;
    }

    private fun bindFilms(result: Result<List<FilmPresentation>>) {
        when (result){
            is Success -> {
                binding.filmsProgressBar.visibility = View.GONE
                binding.filmsErrorLayout.visibility = View.GONE
                binding.filmsSuccessLayout.visibility = View.VISIBLE
                binding.films = result.data
            }
            is Error -> {
                binding.filmsProgressBar.visibility = View.GONE
                binding.filmsErrorLayout.visibility = View.VISIBLE
                binding.filmsSuccessLayout.visibility = View.GONE
                binding.tvFilmsError.text = result.message
            }
            is Loading -> binding.filmsProgressBar.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}