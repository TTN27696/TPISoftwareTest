package com.example.tpisoftwaretest.presentation.main.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.tpisoftwaretest.R
import com.example.tpisoftwaretest.data.model.entity.Place
import com.example.tpisoftwaretest.databinding.FragmentPlaceDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentPlaceDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    // Handle the back button event
                    findNavController().popBackStack(R.id.taipeiTourFragment, true)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_place_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        arguments?.let {
            val data = DetailFragmentArgs.fromBundle(it).taskItem
            if (data != null) {
                loadData(data)
            }
        }
    }

    private fun loadData(place: Place) {
        with(binding) {
            toolbar.title = place.name
                if (place.images.isNotEmpty()) {
                Glide.with(binding.root.context)
                    .load(place.images[0].src)
                    .centerCrop()
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_loading)
                    .into(imageDetail)
            }
            textTitle.text = place.name
            textContent.text = place.textContentDetail()
            textUrl.text = place.url
        }
    }
}