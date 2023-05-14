package com.fitpeo.test.jogendra

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Looper
import android.widget.Toast
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@InstallIn(SingletonComponent::class)
@Module
class AppUtil @Inject constructor(){

        fun isNetworkAvailable(@ApplicationContext appContext: Context): Boolean {
            val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            var hasInternet = false
            capabilities?.let {
                hasInternet = it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            }
            return hasInternet
        }

        fun showMsgNetworkNotAvailable(@ApplicationContext appContext: Context) {
            Looper.prepare()
            Toast.makeText(appContext,"No network!!", Toast.LENGTH_LONG).show()
        }

}