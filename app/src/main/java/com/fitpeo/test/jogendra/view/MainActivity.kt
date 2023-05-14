package com.fitpeo.test.jogendra.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fitpeo.test.jogendra.R
import com.fitpeo.test.jogendra.databinding.ActivityMainBinding
import com.fitpeo.test.jogendra.view.adapters.PhotosAdapter
import com.fitpeo.test.jogendra.viewmodals.PhotosViewModal
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mPhotosViewModal : PhotosViewModal

    @Inject lateinit var photosAdapter: PhotosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        mPhotosViewModal = ViewModelProvider(this)[PhotosViewModal::class.java]

        mPhotosViewModal.loadPhotos()
        mPhotosViewModal.isPhotosAvailable().observe(this) {
            if (it) {
                binding.pBarLoading.visibility = View.GONE
            } else {
                binding.pBarLoading.visibility = View.VISIBLE
            }
        }

        mPhotosViewModal.photosList().observe(this@MainActivity) {
            binding.rvPhotoList.layoutManager = LinearLayoutManager(this@MainActivity).also { layoutManager ->
                layoutManager.orientation = RecyclerView.VERTICAL
            }
            photosAdapter.mPhotosList = it
            photosAdapter.mContext = this@MainActivity
            binding.rvPhotoList.adapter = photosAdapter
            binding.pBarLoading.visibility = View.GONE
        }
    }
}