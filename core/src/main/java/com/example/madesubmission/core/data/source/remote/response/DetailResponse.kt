package com.example.madesubmission.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailResponse(

    @field:SerializedName("genres")
    var genres : List<GenreResponse>,

    @field:SerializedName("runtime")
    var runTime: Int?,

    @field:SerializedName("tagline")
    val tagLine: String?
)