package com.example.madesubmission.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMoviesResponse(
    @field:SerializedName("results")
    val movies: List<MovieResponse>
)