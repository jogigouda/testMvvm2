package com.fitpeo.test.jogendra.view.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fitpeo.test.jogendra.R
import com.fitpeo.test.jogendra.databinding.ItemPhotosBinding
import com.fitpeo.test.jogendra.modal.data.Photo
import com.fitpeo.test.jogendra.view.DetailedActivity
import com.squareup.picasso.Picasso
import javax.inject.Inject

class PhotosAdapter @Inject constructor(): RecyclerView.Adapter<PhotosAdapter.PhotosHolder>() {


    lateinit var mPhotosList: List<Photo>
    lateinit var  mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_photos, parent, false)
        val binding = ItemPhotosBinding.bind(view)
        return PhotosHolder(binding)
    }

    override fun getItemCount(): Int {
        return mPhotosList.size
    }

    override fun onBindViewHolder(holder: PhotosHolder, position: Int) {
        val photo = mPhotosList[position]

        // sets the image to the imageview from our itemHolder class
        holder.loadImage(photo.thumbnailUrl)
        //Picasso.get().load(photo.thumbnailUrl).placeholder(R.drawable.loading).into(holder.thumbPhoto)

        // sets the text to the textview from our itemHolder class
        holder.setTitle(photo.title)

        holder.setItemClick(mContext,photo)
    }



    class PhotosHolder(binding: ItemPhotosBinding) : RecyclerView.ViewHolder(binding.root) {
        val bindingItem = binding

        fun setTitle(title: String?) {
            bindingItem.title.text = title
        }

        fun loadImage(url: String?) {
            Picasso.get().load(url).placeholder(R.drawable.loading).into(bindingItem.thumbnail)
        }

        fun setItemClick(context: Context,photo:Photo) {
            bindingItem.root.setOnClickListener {
                val intent = Intent(context,DetailedActivity::class.java)
                intent.putExtra("PHOTO_DETAIL",photo)
                context.startActivity(intent)
            }
        }
    }
}


