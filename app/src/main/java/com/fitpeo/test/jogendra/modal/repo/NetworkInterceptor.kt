package com.fitpeo.test.jogendra.modal.repo


import android.os.Looper
import android.widget.Toast
import com.fitpeo.test.jogendra.MyApplication
import dagger.Provides
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit


abstract class NetworkInterceptor : Interceptor {

    abstract fun isInternetAvailable(): Boolean
    abstract fun showMsgNetworkNotAvailable()

    //@Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder: Request.Builder = chain.request().newBuilder()

        //ref https://amitshekhar.me/blog/caching-with-okhttp-interceptor-and-retrofit
        if (!isInternetAvailable()) {
            requestBuilder.cacheControl(CacheControl.FORCE_CACHE);
            //throw java.lang.Exception("Network not available!!")
            showMsgNetworkNotAvailable()
        }
        val response = chain.proceed(requestBuilder.build())
        //Catching response for 10 days
        val cacheControl = CacheControl.Builder()
            .maxAge(10, TimeUnit.DAYS)
            .build()

        return response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()
    }
}