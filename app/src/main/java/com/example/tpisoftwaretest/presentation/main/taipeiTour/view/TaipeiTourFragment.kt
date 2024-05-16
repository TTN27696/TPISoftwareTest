package com.example.tpisoftwaretest.presentation.main.taipeiTour.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tpisoftwaretest.R
import com.example.tpisoftwaretest.databinding.FragmentTaipeiTourBinding

class TaipeiTourFragment : Fragment() {

    private lateinit var binding: FragmentTaipeiTourBinding

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
    }
}