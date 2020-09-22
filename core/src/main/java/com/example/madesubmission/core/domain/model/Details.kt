package com.example.madesubmission.core.domain.model

data class Details(
    var genres : ArrayList<String>,
    var runTime: Int?,
    val tagLine: String?
)