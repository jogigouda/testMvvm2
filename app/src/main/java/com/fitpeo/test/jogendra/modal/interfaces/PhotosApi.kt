package com.fitpeo.test.jogendra.modal.interfaces

import com.fitpeo.test.jogendra.modal.data.Photo
import retrofit2.Response
import retrofit2.http.GET

interface PhotosApi {
    @GET("/photos")
    suspend fun getPhotos() : Response<List<Photo>>
}