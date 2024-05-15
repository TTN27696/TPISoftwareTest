package com.example.tpisoftwaretest.data.model.dto

import com.example.tpisoftwaretest.data.model.entity.Place
import com.google.gson.annotations.SerializedName

data class PlaceDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("introduction")
    val introduction: String,
    @SerializedName("open_time")
    val openTime: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("images")
    val images: List<ImageDTO>
)

fun PlaceDTO.mapToPlace() = Place(
    id = id,
    name = name,
    introduction = introduction,
    openTime = openTime,
    address = address,
    url = url,
    images = images.mapToImages()
)

fun List<PlaceDTO>.mapToPlaces() = map {
    it.mapToPlace()
}

