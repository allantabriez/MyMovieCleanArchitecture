package com.example.madesubmission.core.utils

object GlideMovie {
    fun createImagePath(path: String): String {
        val baseUrl = "https://image.tmdb.org/t/p/w300"
        return baseUrl + path
    }
}