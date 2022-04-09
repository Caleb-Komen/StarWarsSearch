package com.arch.starwarssearch.ui.charactersearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.arch.starwarssearch.R
import com.arch.starwarssearch.databinding.FragmentCharacterSearchBinding
import com.arch.starwarssearch.model.CharacterPresentation
import com.arch.starwarssearch.util.Result
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterSearchFragment : Fragment() {
    private var _binding: FragmentCharacterSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CharacterSearchViewModel by viewModels()

    private val adapter = CharactersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_character_search, container, false)
        _binding = FragmentCharacterSearchBinding.bind(view)
        binding.rvCharacters.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.characters.observe(viewLifecycleOwner){ result ->
            filterTypes(result)
        }

        binding.etSearchField.afterTextChanged {
            viewModel.setSearchQuery(it)
        }
    }

    private fun filterTypes(result: Result<List<CharacterPresentation>>){
        when (result){
            is Result.Success -> {
                binding.linearProgress.visibility = View.GONE
                binding.characters = result.data
                if (result.data?.isEmpty()!!) showSnackbar(getString(R.string.no_result))
            }
            is Result.Error -> {
                binding.linearProgress.visibility = View.GONE
                showSnackbar(result.message!!)
            }
            is Result.Loading -> {
                binding.linearProgress.visibility = View.VISIBLE
            }
        }
    }

    private fun showSnackbar(message: String){
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }

    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit){
        this.addTextChangedListener {
            afterTextChanged.invoke(it.toString())
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}