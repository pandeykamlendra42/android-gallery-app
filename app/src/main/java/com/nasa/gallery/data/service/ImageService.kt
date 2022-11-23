package com.nasa.gallery.data.service

import com.google.gson.JsonArray
import com.nasa.gallery.data.model.Image
import com.nasa.gallery.data.model.ImagesList
import retrofit2.Response
import retrofit2.http.GET

interface ImageService {
    @GET("obvious/take-home-exercise-data/trunk/nasa-pictures.json")
    suspend fun getImages() : Response<List<Image>>
}