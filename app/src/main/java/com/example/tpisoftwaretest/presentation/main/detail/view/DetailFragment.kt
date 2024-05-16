package com.example.tpisoftwaretest.presentation.main.detail.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.tpisoftwaretest.R
import com.example.tpisoftwaretest.data.model.entity.Place
import com.example.tpisoftwaretest.databinding.FragmentPlaceDetailBinding
import com.example.tpisoftwaretest.utility.setTextHighLight
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentPlaceDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (binding.webPlace.visibility == View.VISIBLE) {
                        binding.webPlace.visibility = View.GONE
                    } else {
                        findNavController().popBackStack(R.id.taipeiTourFragment, true)
                    }
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_place_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupWebView()

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
            textUrl.setTextHighLight(place.url) {
                webPlace.visibility = View.VISIBLE
                webPlace.loadUrl(place.url)
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        with(binding.webPlace) {
            webViewClient = WebViewClient()
            setLayerType(View.LAYER_TYPE_HARDWARE, null)
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.setSupportZoom(true)
            settings.builtInZoomControls = true
            Runnable { settings.displayZoomControls = false }.run()
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
            isScrollbarFadingEnabled = false
        }
    }
}