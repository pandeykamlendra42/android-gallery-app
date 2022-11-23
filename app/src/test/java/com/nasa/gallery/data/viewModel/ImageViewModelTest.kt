package com.nasa.gallery.data.viewModel

import com.nasa.gallery.data.model.Image
import com.nasa.gallery.data.resource.Resource
import com.nasa.gallery.data.service.ImageRemoteDataSource
import com.nasa.gallery.data.service.ImageService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class ImageViewModelTest {

    lateinit var imageViewModel: ImageViewModel


    lateinit var imageRemoteDataSource: ImageRemoteDataSource

    @Mock
    lateinit var imageService: ImageService

    private fun getImage(n: Int): Image {
        return Image("T_$n", "E_$n", "HD_$n", "U_$n", "2019-12-10$n", "C_$n")
    }

    private fun mockDataResource(): Resource<List<Image>> {
        val list = listOf<Image>(getImage(0), getImage(1), getImage(3))
        return Resource.success(list);
    }
    private fun mockDataResponse(): Response<List<Image>> {
        val list = listOf<Image>(getImage(0), getImage(1), getImage(3))
        return Response.success(list);
    }

    @Before
    fun setUp() {
        imageRemoteDataSource = ImageRemoteDataSource(imageService)
        imageViewModel = ImageViewModel(imageRemoteDataSource)
    }

    @Test
    fun repositoryResultSuccess() = runBlocking {
        Mockito.`when`(imageService.getImages()).thenReturn(mockDataResponse())
        val images = imageRemoteDataSource.getImages()
        assert(images.data!!.size == 3)
    }

    @Test
    fun getSortedListByDate() {
        val images = imageViewModel.sortedList(mockDataResource().data!!)
        assert(images[0].title == "T_3")
    }

}