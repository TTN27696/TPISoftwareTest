package com.example.tpisoftwaretest.data.api

import com.example.tpisoftwaretest.data.model.dto.PlacesDTO
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {

    @Headers("Accept: application/json")
    @GET("{lang}/Attractions/All")
    suspend fun getPlaces(@Path("lang") lang: String): PlacesDTO
}