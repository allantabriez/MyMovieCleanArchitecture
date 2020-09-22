package com.example.madesubmission.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.madesubmission.core.domain.model.Details
import com.example.madesubmission.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovies(): Flow<List<Movie>>

    fun getFavoriteMovies(): Flow<List<Movie>>

    fun checkFavoriteMovie(id: Int): LiveData<Boolean>

    fun insertFavoriteMovie(movie: Movie)

    fun deleteFavoriteMovie(movie: Movie)

    fun getMovieDetails(id: Int): Flow<Details>
}