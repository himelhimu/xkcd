package com.himel.apps.xkcd.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.himel.apps.xkcd.databinding.FragmentHomeBinding
import com.himel.apps.xkcd.models.Comic
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    lateinit var homeViewModel : HomeViewModel

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setUpObserver()
        homeViewModel.getComicData(0)
        return root
    }

    private fun setUpObserver(){
        homeViewModel.comicData.observe(viewLifecycleOwner) {
            if (it != Comic.EMPTY) {
                showData(it)
            }
        }
    }

    private fun showData(it: Comic) {
        binding.altText.text = it.alt
        binding.webview.loadUrl(it.img)
        binding.webview.settings.builtInZoomControls = true
        binding.webview.settings.displayZoomControls = true
        //Glide.with(this).load(it.img).into(binding.img)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}