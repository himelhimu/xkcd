package com.himel.apps.xkcd.ui.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.himel.apps.xkcd.R
import com.himel.apps.xkcd.databinding.SearchFragmentBinding
import com.himel.apps.xkcd.models.Comic
import com.himel.apps.xkcd.network.ApiService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {



    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SearchFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
        setUpObserver()
        binding.number.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (!p0.isNullOrEmpty()){
                    binding.progressbar.visibility = View.VISIBLE
                    viewModel.getComicData(binding.number.text.toString().toInt())
                }else binding.progressbar.visibility = View.GONE

            }

        })
    }

    private fun setUpObserver(){
        viewModel.comicData.observe(viewLifecycleOwner) {
            if (it != null) {
                showData(it)
            }
        }
    }
    private fun showData(it: Comic) {
        binding.altText.text = it.alt
        binding.webview.loadUrl(it.img)
        binding.webview.settings.builtInZoomControls = true
        binding.webview.zoomOut()
        binding.webview.settings.displayZoomControls = true
        //Glide.with(this).load(it.img).into(binding.img)
    }
}