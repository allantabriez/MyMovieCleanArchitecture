package com.example.madesubmission.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madesubmission.core.data.source.remote.network.ApiService
import com.example.madesubmission.core.data.source.remote.response.DetailResponse
import com.example.madesubmission.core.data.source.remote.response.ListMoviesResponse
import com.example.madesubmission.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.Exception

class RemoteDataSource(private val apiService: ApiService){
    fun getAllMovies(): Flow<List<MovieResponse>>{
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.movies
                Log.d("RemoteDataSource", dataArray.toString())
                if (dataArray.isNotEmpty()) emit(dataArray)
            } catch (e: Exception){
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getMovieDetails(id: Int): Flow<DetailResponse> {
        return flow {
            try {
                val response = apiService.getDetails(id.toString())
                emit(response)
            } catch (e: Exception){
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}