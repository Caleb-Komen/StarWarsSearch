package com.arch.starwarssearch.ui.characterdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.arch.starwarssearch.databinding.FragmentCharacterDetailBinding
import com.arch.starwarssearch.model.*
import com.arch.starwarssearch.util.Result
import com.arch.starwarssearch.util.Result.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {
    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CharacterDetailViewModel by viewModels()

    private val args: CharacterDetailFragmentArgs by navArgs()

    private val speciesAdapter = SpeciesAdapter()

    private val filmsAdapter = FilmsAdapter()

    private val starshipsAdapter = StarshipsAdapter()

    private val vehiclesAdapter = VehiclesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false).apply {
            character = args.character
            rvSpecies.adapter = speciesAdapter
            rvFilms.adapter = filmsAdapter
            rvStarships.adapter = starshipsAdapter
            rvVehicles.adapter = vehiclesAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setCharacterUrl(args.character.url)
        setupObservers()
    }

    private fun setupObservers(){
        viewModel.planet.observe(viewLifecycleOwner){
            val result = it ?: return@observe
            bindPlanet(result)
        }
        viewModel.species.observe(viewLifecycleOwner){
            val result = it ?: return@observe
            bindSpecies(result)
        }
        viewModel.films.observe(viewLifecycleOwner){
            val result = it ?: return@observe
            bindFilms(result)
        }
        viewModel.starships.observe(viewLifecycleOwner){
            val result = it ?: return@observe
            bindStarships(result)

        }
        viewModel.vehicles.observe(viewLifecycleOwner){
            val result = it ?: return@observe
            bindVehicles(result)
        }
    }

    private fun bindVehicles(result: Result<List<VehiclePresentation>>) {
        when (result){
            is Success -> {
                binding.vehiclesProgressBar.visibility = View.GONE
                binding.vehiclesErrorLayout.visibility = View.GONE
                binding.vehiclesSuccessLayout.visibility = View.VISIBLE
                binding.vehicles = result.data
            }
            is Error -> {
                binding.vehiclesProgressBar.visibility = View.GONE
                binding.vehiclesErrorLayout.visibility = View.VISIBLE
                binding.vehiclesSuccessLayout.visibility = View.GONE
                binding.tvVehiclesError.text = result.message
            }
            is Loading -> binding.vehiclesProgressBar.visibility = View.VISIBLE
        }
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

    private fun bindPlanet(result: Result<PlanetPresentation>) {
        when (result){
            is Success -> {
                binding.planetProgressBar.visibility = View.GONE
                binding.planetErrorLayout.visibility = View.GONE
                binding.planetSuccessLayout.visibility = View.VISIBLE
                binding.planet = result.data
            }
            is Error -> {
                binding.planetProgressBar.visibility = View.GONE
                binding.planetErrorLayout.visibility = View.VISIBLE
                binding.planetSuccessLayout.visibility = View.GONE
                binding.tvPlanetError.text = result.message
            }
            is Loading -> binding.planetProgressBar.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}