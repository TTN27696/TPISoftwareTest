package com.example.tpisoftwaretest.data.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Place(
    val id: Int = -1,
    val name: String = "",
    val introduction: String = "",
    val openTime: String = "",
    val address: String = "",
    val url: String = "",
    val images: List<Image> = emptyList()
): Parcelable {
    fun textContentDetail() = "$introduction \n $address \n $openTime"
}
