package com.example.tpisoftwaretest.presentation.main.taipeiTour.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tpisoftwaretest.R
import com.example.tpisoftwaretest.data.model.dto.mapToPlaces
import com.example.tpisoftwaretest.databinding.FragmentTaipeiTourBinding
import com.example.tpisoftwaretest.presentation.main.taipeiTour.adapter.TaipeiTourAdapter
import com.example.tpisoftwaretest.presentation.main.viewModel.MainViewModel
import com.example.tpisoftwaretest.utility.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaipeiTourFragment : Fragment() {

    private lateinit var binding: FragmentTaipeiTourBinding
    private val mainViewModel by viewModels<MainViewModel>()

    private val taipeiTourAdapter by lazy {
        TaipeiTourAdapter(
            places = arrayListOf(),
            navigateToDetail = {
                findNavController().navigate(R.id.action_taipeiTourFragment_to_detailFragment)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_taipei_tour, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupRV()
        loadPlaces()
    }

    private fun setupRV() {
        with(binding.recyclerPlaces) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = taipeiTourAdapter
        }
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }

    private fun loadPlaces() {
        with(binding) {
            mainViewModel.fetchPlaces().observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        it.data?.let { placesDTO -> taipeiTourAdapter.updatePlaces(placesDTO.data.mapToPlaces()) }
                        recyclerPlaces.visibility = View.VISIBLE
                    }

                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerPlaces.visibility = View.GONE
                    }

                    Status.ERROR -> {
                        //Handle Error
                        progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun updateLang(lang: String) {
        mainViewModel.lang = lang
    }
}