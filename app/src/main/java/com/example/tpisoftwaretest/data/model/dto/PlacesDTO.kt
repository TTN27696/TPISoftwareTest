package com.example.tpisoftwaretest.data.model.dto

import com.google.gson.annotations.SerializedName

data class PlacesDTO(
    @SerializedName("total")
    val total: Int,
    @SerializedName("data")
    val data: List<PlaceDTO>
)