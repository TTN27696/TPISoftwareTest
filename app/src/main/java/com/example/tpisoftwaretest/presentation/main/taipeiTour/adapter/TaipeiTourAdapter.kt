package com.example.tpisoftwaretest.presentation.main.taipeiTour.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tpisoftwaretest.data.model.entity.Place
import com.example.tpisoftwaretest.databinding.ItemPlaceBinding

class TaipeiTourAdapter(
    private val places: ArrayList<Place>,
    private val navigateToDetail: (Place) -> Unit
) : RecyclerView.Adapter<TaipeiTourAdapter.PlaceViewHolder>() {

    class DiffCallback(private val oldList: List<Place>, private val newList: List<Place>) :
        DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

    }

    fun updatePlaces(newPlaces: List<Place>) {
        val diffCallback = DiffCallback(places, newPlaces)
        val diffPlaces = DiffUtil.calculateDiff(diffCallback)
        places.clear()
        places.addAll(newPlaces)
        diffPlaces.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val binding = ItemPlaceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PlaceViewHolder(binding)
    }

    override fun getItemCount() = places.size

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bindItem(places[position])
    }

    inner class PlaceViewHolder(private val binding: ItemPlaceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(item: Place) {
            with(binding) {
                place = item
                executePendingBindings()
            }
        }

        init {
            binding.cardPlace.setOnClickListener {
                Log.d("AAAA", "adapterPosition: $adapterPosition")
                navigateToDetail.invoke(places[adapterPosition])
            }
        }
    }


}