package com.example.tpisoftwaretest.data.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    val src: String
) : Parcelable