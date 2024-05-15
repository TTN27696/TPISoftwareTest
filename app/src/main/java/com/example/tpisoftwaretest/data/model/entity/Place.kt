package com.example.tpisoftwaretest.data.model.entity

data class Place(
    val id: Int,
    val name: String,
    val introduction: String,
    val openTime: String,
    val address: String,
    val url: String,
    val images: List<Image>
)
