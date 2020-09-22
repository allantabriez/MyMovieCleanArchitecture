package com.example.madesubmission.core.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madesubmission.core.data.source.local.entity.MovieEntity
import com.example.madesubmission.core.data.source.remote.response.DetailResponse
import com.example.madesubmission.core.data.source.remote.response.MovieResponse
import com.example.madesubmission.core.domain.model.Details
import com.example.madesubmission.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToDomain(input: List<MovieResponse>): List<Movie> {
        val movieList = ArrayList<Movie>()
        input.map {
            val movie = Movie(
                it.id,
                it.title,
                it.releaseDate,
                it.overView,
                it.imagePath,
                it.voteAverage,
                it.voteCount,
                it.popularity
            )
            movieList.add(movie)
        }
        Log.d("DataMapper", movieList.toString())
        return movieList
    }

    fun mapResponseToDomain(input: DetailResponse): Details {
        val genres = ArrayList<String>()
        for (i in input.genres){
            genres.add(i.genre)
        }
        return Details(
            genres,
            input.runTime,
            input.tagLine
        )
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> {
        val movieList = ArrayList<Movie>()
        input.map {
            val movie = Movie(
                it.id,
                it.title,
                it.releaseDate,
                it.overView,
                it.imagePath,
                it.voteAverage,
                it.voteCount,
                it.popularity
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapDomainToEntity(input: Movie): MovieEntity {
        return MovieEntity(
            input.id,
            input.title,
            input.releaseDate,
            input.overView,
            input.imagePath,
            input.voteAverage,
            input.voteCount,
            input.popularity
        )
    }
}