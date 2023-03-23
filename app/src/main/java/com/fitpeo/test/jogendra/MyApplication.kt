package com.fitpeo.test.jogendra

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.os.Looper
import android.widget.Toast
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApplication: Application() {

    companion object{
        lateinit var appContext:Context
    }
    override fun onCreate() {
        super.onCreate()
        appContext =  this@MyApplication
    }


}