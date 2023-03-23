package com.fitpeo.test.jogendra.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fitpeo.test.jogendra.R
import com.fitpeo.test.jogendra.databinding.ActivityMainBinding
import com.fitpeo.test.jogendra.modal.repo.Repository
import com.fitpeo.test.jogendra.view.adapters.PhotosAdapter
import com.fitpeo.test.jogendra.viewmodals.PhotosViewModal
import com.fitpeo.test.jogendra.viewmodals.PhotosViewModalFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"

    lateinit var mPhotosViewModal : PhotosViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this@MainActivity,R.layout.activity_main)

        val repository = Repository.getInstance()
        mPhotosViewModal = ViewModelProvider(this@MainActivity,PhotosViewModalFactory(repository)).get(PhotosViewModal::class.java)

        mPhotosViewModal.loadPhotos()
        mPhotosViewModal.isPhotosAvailable().observe(this@MainActivity, Observer {
            if(it){
                binding.pBarLoading.visibility = View.GONE
            }
            else{
                binding.pBarLoading.visibility = View.VISIBLE
            }
        })

        mPhotosViewModal.photosList().observe(this@MainActivity, Observer {
            binding.rvPhotoList.layoutManager = LinearLayoutManager(this@MainActivity).also {
                it.orientation = RecyclerView.VERTICAL
            }
            val adapter = PhotosAdapter(it,this@MainActivity)
            binding.rvPhotoList.adapter = adapter
            binding.pBarLoading.visibility = View.GONE
        })
    }
}