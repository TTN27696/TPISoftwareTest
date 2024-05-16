package com.example.tpisoftwaretest.presentation.main.taipeiTour.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tpisoftwaretest.R
import com.example.tpisoftwaretest.data.model.entity.Place
import com.example.tpisoftwaretest.databinding.FragmentTaipeiTourBinding
import com.example.tpisoftwaretest.presentation.main.taipeiTour.adapter.TaipeiTourAdapter

class TaipeiTourFragment : Fragment() {

    private lateinit var binding: FragmentTaipeiTourBinding

    private val taipeiTourAdapter by lazy {
        TaipeiTourAdapter(
            places = arrayListOf(),
            navigateToDetail = {

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
//        binding.buttonChangeFrag.setOnClickListener {
//            findNavController().navigate(R.id.action_taipeiTourFragment_to_detailFragment)
//        }
        with(binding) {
            (activity as AppCompatActivity).setSupportActionBar(toolbar)
            toolbar.title = "This is TaipeiTourFragment"
            recyclerPlaces.layoutManager = LinearLayoutManager(requireContext())
            recyclerPlaces.adapter = taipeiTourAdapter

            taipeiTourAdapter.updatePlaces(
                arrayListOf<Place>().apply {
                    for (i in 0..9) {
                        add(
                            Place(
                                id = i,
                                name = "Place $i",
                                introduction = "This is place $i",
                                openTime = "$i am",
                                address = "This is $i address",
                                url = "",
                                images = emptyList()
                            )
                        )
                    }
                }
            )
        }
    }
}