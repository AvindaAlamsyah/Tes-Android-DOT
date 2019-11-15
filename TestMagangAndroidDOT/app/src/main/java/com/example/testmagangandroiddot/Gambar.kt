package com.example.testmagangandroiddot

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Gambar(

    val caption: String,
    val thumbnail: String,
    val image: String

) : Parcelable