package com.fitpeo.test.jogendra.viewmodals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fitpeo.test.jogendra.modal.repo.Repository

class PhotosViewModalFactory (private val helper: Repository ) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PhotosViewModal(helper) as T
    }
}