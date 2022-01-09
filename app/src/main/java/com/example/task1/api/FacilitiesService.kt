package com.example.task1.api

import com.example.task1.response.FaclitiesResponse
import retrofit2.Call
import retrofit2.http.GET

interface FacilitiesService {
    @GET("ricky1550/pariksha/db")
    fun getFacilitiesList() : Call<FaclitiesResponse>
}