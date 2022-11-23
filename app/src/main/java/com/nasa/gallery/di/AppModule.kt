package com.nasa.gallery.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nasa.gallery.data.repository.ImageRepository
import com.nasa.gallery.data.service.ImageRemoteDataSource
import com.nasa.gallery.data.service.ImageService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideImageService(retrofit: Retrofit): ImageService = retrofit.create(ImageService::class.java)

    @Singleton
    @Provides
    fun provideImageRemoteDataSource(imageService: ImageService) = ImageRemoteDataSource(imageService)

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: ImageRemoteDataSource) =
        ImageRepository(remoteDataSource)
}