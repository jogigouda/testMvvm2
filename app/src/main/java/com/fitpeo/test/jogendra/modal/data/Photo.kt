package com.fitpeo.test.jogendra.modal.data

import android.os.Parcel
import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.fitpeo.test.jogendra.R
import com.squareup.picasso.Picasso


data class Photo( val albumId: Int, val id: Int, val title: String?, val url: String?, val thumbnailUrl: String?) : Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(albumId)
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(url)
        parcel.writeString(thumbnailUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Photo> {
        override fun createFromParcel(parcel: Parcel): Photo {
            return Photo(parcel)
        }

        override fun newArray(size: Int): Array<Photo?> {
            return arrayOfNulls(size)
        }
    }

//    @BindingAdapter("photoDetail:url")
//    fun loadImage(view: ImageView, imageUrl: String?) {
//        Picasso.get().load(imageUrl).placeholder(R.drawable.loading).into(view)
//    }


}

//Data for json elements
//"albumId": 3,
//    "id": 111,
//    "title": "quos rem nulla ea amet",
//    "url": "https://via.placeholder.com/600/cc178e",
//    "thumbnailUrl": "https://via.placeholder.com/150/cc178e"
