package com.nasa.gallery.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nasa.gallery.data.viewModel.ImageViewModel
import com.nasa.gallery.databinding.FragmentImageDetailViewBinding
import com.nasa.gallery.ui.adaptor.DetailsItemAdapter

class ImageDetailViewFragment : Fragment() {

    lateinit var viewModel: ImageViewModel
    lateinit var detailsItemAdapter: DetailsItemAdapter
    private var _binding: FragmentImageDetailViewBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentImageDetailViewBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        viewModel = ViewModelProvider(requireActivity())[ImageViewModel::class.java]
        val images = viewModel.result.value!!.data!!
        detailsItemAdapter = DetailsItemAdapter(images.sortedByDescending { item -> item.date })
        _binding!!.pager.apply {

            adapter = detailsItemAdapter
            arguments?.let {
                val position = it.getString("position") ?: "0"
                setCurrentItem(position.toInt(), false)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}