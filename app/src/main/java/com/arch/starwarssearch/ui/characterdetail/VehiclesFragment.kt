package com.arch.starwarssearch.ui.characterdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.arch.starwarssearch.databinding.FragmentVehiclesBinding
import com.arch.starwarssearch.model.VehiclePresentation
import com.arch.starwarssearch.util.Result
import com.arch.starwarssearch.util.Result.*

class VehiclesFragment : Fragment() {
    private var _binding: FragmentVehiclesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CharacterDetailViewModel by activityViewModels()

    private val vehiclesAdapter = VehiclesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVehiclesBinding.inflate(inflater, container, false).apply {
            rvVehicles.adapter = vehiclesAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}