package com.example.submission_4.ui.home

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    var title: String = "",
    var release: String = "",
    var language: String = "",
    var popularity: Int = 0,
    var vote: Int = 0,
    var synopsis: String = "",
    var poster: String = ""
) : Parcelable