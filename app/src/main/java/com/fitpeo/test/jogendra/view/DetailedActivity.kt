package com.fitpeo.test.jogendra.view

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.fitpeo.test.jogendra.R
import com.fitpeo.test.jogendra.databinding.ActivityDetailBinding
import com.fitpeo.test.jogendra.modal.data.Photo
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDetailBinding = DataBindingUtil.setContentView(this@DetailedActivity,R.layout.activity_detail)

        title = getString(R.string.imageDetail)

        //Get the Parcelable object
        val photoDetail : Photo? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("PHOTO_DETAIL", Photo::class.java)
        } else {
            intent.getParcelableExtra("PHOTO_DETAIL")
        }

        Picasso.get().load(photoDetail?.url).placeholder(R.drawable.loading).into(binding.image)
        binding.photoDetail = photoDetail
    }
}