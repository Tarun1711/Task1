package com.example.task1.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task1.response.FaclitiesResponse
import com.example.task1.Utils.getHttpClientBuilder
import com.example.task1.api.FacilitiesService
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {
    var facilitiesResponse = MutableLiveData<FaclitiesResponse>()
    var BASE_URL = "https://my-json-server.typicode.com/"
    lateinit var httpClient: OkHttpClient
    lateinit var retrofit: Retrofit


    fun getData() {
        httpClient = getHttpClientBuilder()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient.newBuilder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val api = retrofit.create(FacilitiesService::class.java)
        val call = api.getFacilitiesList()

        call.enqueue(object : Callback<FaclitiesResponse> {
            override fun onFailure(call: Call<FaclitiesResponse>, t: Throwable) {
                //Toast.makeText(this ,"On failure",Toast.LENGTH_SHORT).show()
                Log.i("h", "on fail" + t.toString())
            }

            override fun onResponse(call: Call<FaclitiesResponse>, response: retrofit2.Response<FaclitiesResponse>) {
                Log.i("d", response.body().toString())
//                progressBar.visibility= View.GONE
                facilitiesResponse.postValue(response.body())
            }
        })
    }
}



