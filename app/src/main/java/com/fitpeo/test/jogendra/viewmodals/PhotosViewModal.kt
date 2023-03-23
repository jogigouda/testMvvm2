package com.fitpeo.test.jogendra.viewmodals

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitpeo.test.jogendra.modal.data.Photo
import com.fitpeo.test.jogendra.modal.interfaces.PhotosApi
import com.fitpeo.test.jogendra.modal.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModal @Inject constructor(val helper:Repository ) : ViewModel() {
    val TAG = "PhotosViewModal"


    private val photosList = MutableLiveData<List<Photo>>()
    private val isPhotosAvailable = MutableLiveData<Boolean>()

    fun photosList(): LiveData<List<Photo>> {
        return photosList
    }

    fun isPhotosAvailable():LiveData<Boolean>{
        return isPhotosAvailable
    }

    fun loadPhotos(){
        getPhotosList()
    }

    private fun getPhotosList(){
        viewModelScope.launch {
            try{
                val response = helper.getPhotosList()
                val photos = response.body()
                if(photos.isNullOrEmpty()){
                    isPhotosAvailable.value = false
                    Log.d(TAG, "No photos available.")
                }
                else{
                    photosList.value = photos
                    isPhotosAvailable.value = true
                    Log.d(TAG, "Photos List:"+photos)
                }
            }
            catch (e:Exception){
                isPhotosAvailable.value = false
                Log.d(TAG, "Error fetching photo list.")
                e.printStackTrace()
            }
        }
    }

}