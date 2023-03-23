package com.fitpeo.test.jogendra.modal.repo

import com.fitpeo.test.jogendra.AppUtil
import com.fitpeo.test.jogendra.MyApplication
import com.fitpeo.test.jogendra.modal.data.Photo
import com.fitpeo.test.jogendra.modal.interfaces.PhotosApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


@Module
@InstallIn(SingletonComponent::class)
class Repository {
    val baseUrl = "https://jsonplaceholder.typicode.com/"

    companion object{
        private lateinit var retrofitHelper: Repository

        @Provides
         fun getInstance(): Repository {
            return if(::retrofitHelper.isInitialized){
                retrofitHelper
            } else{
                retrofitHelper = Repository()
                retrofitHelper
            }
        }
    }



    private fun createRetrofitInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .client(provideOkHttpClient())
            // we need to add converter factory to
            // convert JSON object to Java object
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    suspend fun getPhotosList() : Response<List<Photo>>{
       return createPhotosApi().getPhotos()
    }

    private fun createPhotosApi():PhotosApi{
        return createRetrofitInstance().create(PhotosApi::class.java)
    }

    private fun provideOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()

        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.readTimeout(30, TimeUnit.SECONDS)

        httpClient.cache(Cache(File(MyApplication.appContext.cacheDir, "http-cache"), 10L * 1024L * 1024L)) // 10 MiB cache file size

        httpClient.addInterceptor(object : NetworkInterceptor() { //Interceptor added
            override fun isInternetAvailable(): Boolean {
               return AppUtil.isNetworkAvailable(MyApplication.appContext)
            }

            override fun showMsgNetworkNotAvailable() {
                AppUtil.showMsgNetworkNotAvailable(MyApplication.appContext)
            }

        })
        return httpClient.build()
    }
}