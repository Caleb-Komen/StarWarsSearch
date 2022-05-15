package com.arch.starwarssearch.ui.characterdetail

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.arch.starwarssearch.R
import com.arch.starwarssearch.databinding.FragmentCharacterDetailBinding
import com.arch.starwarssearch.model.*
import com.arch.starwarssearch.util.Result
import com.arch.starwarssearch.util.Result.*
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {
    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CharacterDetailViewModel by activityViewModels()

    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false).apply {
            character = args.character
        }
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        binding.tvNameInitials.text = extractCharacterNameInitials(args.character.name)
        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager
        viewPager.adapter = CharacterDetailPagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
        setHasOptionsMenu(true)
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

    private fun getTabTitle(position: Int): String?{
        return when (position){
            FILMS_PAGE_INDEX -> getString(R.string.films)
            SPECIES_PAGE_INDEX -> getString(R.string.species)
            STARSHIPS_PAGE_INDEX -> getString(R.string.starships)
            VEHICLES_PAGE_INDEX -> getString(R.string.vehicles)
            else -> null
        }
    }

    private fun extractCharacterNameInitials(characterName: String): String{
        val nameList = characterName.split(" ")
        val initials = StringBuilder()
        for (name in nameList){
            initials.append(name[0])
        }
        return initials.toString().uppercase()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_character_details, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.action_save -> {
                viewModel.saveCharacter(args.character)
                return true
            }
            R.id.action_delete -> {
                viewModel.deleteCharacter(args.character.url)
                navigateBack()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateBack(){
        val action =
            CharacterDetailFragmentDirections.actionCharacterDetailFragmentToStarWarsFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}