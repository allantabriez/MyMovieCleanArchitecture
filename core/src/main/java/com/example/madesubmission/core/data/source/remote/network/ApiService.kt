package com.example.madesubmission.core.data.source.remote.network

import com.example.madesubmission.core.BuildConfig
import com.example.madesubmission.core.data.source.remote.response.DetailResponse
import com.example.madesubmission.core.data.source.remote.response.ListMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("now_playing?api_key=${BuildConfig.API_KEY}&language=en-US&page=1")
    suspend fun getList(): ListMoviesResponse

    @GET("{id}?api_key=edb370875ba54a43a0f4e98dae724db2&language=en-US")
    suspend fun getDetails(@Path("id") id: String): DetailResponse
}