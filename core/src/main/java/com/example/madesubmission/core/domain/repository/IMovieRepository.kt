package com.example.madesubmission.core.domain.repository

import androidx.lifecycle.LiveData
import com.example.madesubmission.core.domain.model.Details
import com.example.madesubmission.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovies(): Flow<List<Movie>>

    fun getFavoriteMovies(): Flow<List<Movie>>

    fun checkFavoriteMovie(id: Int): LiveData<Boolean>

    fun insertMovie(movie: Movie)

    fun deleteMovie(movie: Movie)

    fun getMovieDetails(id: Int): Flow<Details>
}