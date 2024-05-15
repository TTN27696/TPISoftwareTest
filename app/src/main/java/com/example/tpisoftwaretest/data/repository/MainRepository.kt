package com.example.tpisoftwaretest.data.repository

import com.example.tpisoftwaretest.data.api.ApiService
import com.example.tpisoftwaretest.data.model.dto.PlacesDTO
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getPlaces(lang: String): PlacesDTO {
        return apiService.getPlaces(lang = lang)
    }
}