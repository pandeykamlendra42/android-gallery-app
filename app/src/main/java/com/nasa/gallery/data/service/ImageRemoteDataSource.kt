package com.nasa.gallery.data.service

import com.nasa.gallery.data.resource.BaseDataSource
import javax.inject.Inject

open class ImageRemoteDataSource @Inject constructor(
    private val imageService: ImageService
) : BaseDataSource() {

    suspend fun getImages() = getResult { imageService.getImages() }
}