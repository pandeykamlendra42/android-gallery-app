package com.nasa.gallery.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.nasa.gallery.R
import com.nasa.gallery.data.model.Image
import com.nasa.gallery.data.resource.Resource
import com.nasa.gallery.data.viewModel.ImageViewModel
import com.nasa.gallery.databinding.FragmentGalleryGridViewBinding
import com.nasa.gallery.ui.adaptor.ImageAdapter


class GalleryGridViewFragment : Fragment() {
    lateinit var viewModel: ImageViewModel
    lateinit var adapter: ImageAdapter
    var imagesList = mutableListOf<Image>()
    private var _binding: FragmentGalleryGridViewBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryGridViewBinding.inflate(inflater, container, false)
        setupRecyclerView()
        return _binding!!.root
    }

    private fun setupObservers() {
        viewModel.result.observe(requireActivity()) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    it.data?.let { images ->
                        imagesList.clear()
                        imagesList.addAll(images)
                        adapter.notifyDataSetChanged()
                    }
                    _binding!!.progressBar.visibility = View.GONE
                }
                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    _binding!!.progressBar.visibility = View.VISIBLE
            }
        }
    }

    private fun setupRecyclerView() {
        viewModel = ViewModelProvider(requireActivity())[ImageViewModel::class.java]
        adapter = ImageAdapter(imagesList) { index ->
            val bundle = Bundle()
            bundle.putString("position", index.toString())
            findNavController().navigate(
                R.id.action_galleryGridViewFragment_to_imageDetailViewFragment,
                bundle
            )
        }
        _binding?.let {
            it.mainRecyclerView.layoutManager = GridLayoutManager(requireActivity(), 2)
            it.mainRecyclerView.adapter = adapter
        }

        setupObservers()
    }


}