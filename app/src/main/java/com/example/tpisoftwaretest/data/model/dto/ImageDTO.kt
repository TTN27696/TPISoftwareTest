package com.example.tpisoftwaretest.data.model.dto

import com.example.tpisoftwaretest.data.model.entity.Image
import com.google.gson.annotations.SerializedName

data class ImageDTO(
    @SerializedName("src")
    val src: String,
    @SerializedName("ext")
    val ext: String
)

fun ImageDTO.mapToImage() = Image(
    src = "$src$ext"
)

fun List<ImageDTO>.mapToImages() = map {
    it.mapToImage()
}