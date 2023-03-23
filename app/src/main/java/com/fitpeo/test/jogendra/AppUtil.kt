package com.fitpeo.test.jogendra

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Looper
import android.widget.Toast

class AppUtil {
    companion object{

        fun isNetworkAvailable(appContext: Context): Boolean {
            val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            var hasInternet = false
            capabilities?.let {
                hasInternet = it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            }
            return hasInternet
        }

        fun showMsgNetworkNotAvailable(appContext: Context) {
            Looper.prepare()
            Toast.makeText(appContext,"No network!!", Toast.LENGTH_LONG).show()
        }
    }
}