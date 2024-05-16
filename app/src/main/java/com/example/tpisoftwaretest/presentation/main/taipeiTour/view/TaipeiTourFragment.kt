package com.example.tpisoftwaretest.presentation.main.taipeiTour.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
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
                val action = TaipeiTourFragmentDirections.actionTaipeiTourFragmentToDetailFragment().setTaskItem(it)
                findNavController().navigate(action)
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
        setupMenu()
        setupToolbar()
        setupRV()
        loadPlaces("zh-tw")
    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_switch_lang, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when(menuItem.itemId){
                    R.id.item_lang -> {
                        openDialog()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
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

    private fun loadPlaces(lang: String) {
        with(binding) {
            mainViewModel.fetchPlaces(lang = lang).observe(viewLifecycleOwner) {
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
                        progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun openDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder
            .setTitle("I am the title")
            .setItems(
                arrayOf("zh-tw", "zh-cn", "en", "ja", "ko", "es", "id", "th", "vi")
            ) { dialog, which ->
                loadPlaces(
                    lang = when (which) {
                        0 -> "zh-tw"
                        1 -> "zh-cn"
                        2 -> "en"
                        3 -> "ja"
                        4 -> "ko"
                        5 -> "es"
                        6 -> "id"
                        7 -> "th"
                        8 -> "vi"
                        else -> "zh-tw"
                    }
                )
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}