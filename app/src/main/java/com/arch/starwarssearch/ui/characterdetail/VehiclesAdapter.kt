package com.arch.starwarssearch.ui.characterdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arch.starwarssearch.databinding.ListItemVehicleBinding
import com.arch.starwarssearch.model.VehiclePresentation

class VehiclesAdapter: ListAdapter<VehiclePresentation, VehiclesAdapter.VehiclesViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiclesViewHolder {
        return VehiclesViewHolder(
            ListItemVehicleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VehiclesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class VehiclesViewHolder(
        private val binding: ListItemVehicleBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(item: VehiclePresentation){
            binding.apply {
                vehicle = item
                executePendingBindings()
            }
        }
    }
}

private val DIFF_UTIL = object : DiffUtil.ItemCallback<VehiclePresentation>(){
    override fun areItemsTheSame(
        oldItem: VehiclePresentation,
        newItem: VehiclePresentation
    ) = oldItem.name == newItem.name

    override fun areContentsTheSame(
        oldItem: VehiclePresentation,
        newItem: VehiclePresentation
    ) = oldItem == newItem
}