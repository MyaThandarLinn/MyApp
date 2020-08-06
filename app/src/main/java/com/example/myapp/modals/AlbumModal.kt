package com.example.myapp.modals

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AlbumModal (
    val albumId     : Int,
    val id          : Int,
    val title       : String,
    val url         : String,
    val thumbnailUrl: String
): Parcelable