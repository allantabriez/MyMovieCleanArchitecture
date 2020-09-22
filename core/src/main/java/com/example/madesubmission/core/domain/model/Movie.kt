package com.example.madesubmission.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var id: Int,
    var title: String,
    var releaseDate: String?,
    var overView: String?,
    var imagePath: String,
    var voteAverage: Double,
    var voteCount: Int,
    var popularity: Double
): Parcelable