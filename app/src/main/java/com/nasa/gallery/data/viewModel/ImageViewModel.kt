package com.nasa.gallery.data.viewModel

import androidx.lifecycle.*
import com.nasa.gallery.data.model.Image
import com.nasa.gallery.data.service.ImageRemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val remoteDataSource: ImageRemoteDataSource,) : ViewModel() {
    var result = liveData {
        emit(remoteDataSource.getImages())
    }

    fun sortedList(images: List<Image>): List<Image> {
        return images.sortedByDescending { item -> item.date }
    }
}