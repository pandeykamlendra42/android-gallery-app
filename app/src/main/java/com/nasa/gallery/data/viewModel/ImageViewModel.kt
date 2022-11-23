package com.nasa.gallery.data.viewModel

import androidx.lifecycle.*
import com.nasa.gallery.data.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val repository: ImageRepository
) : ViewModel() {
    var result = liveData {
        emit(repository.getImages())
    }
}