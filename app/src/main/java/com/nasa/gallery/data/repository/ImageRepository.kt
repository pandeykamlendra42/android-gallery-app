package com.nasa.gallery.data.repository

import com.nasa.gallery.data.service.ImageRemoteDataSource
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val remoteDataSource: ImageRemoteDataSource,
) {
    suspend fun getImages() = remoteDataSource.getImages()
}