package com.example.madesubmission.core.domain.repository

import androidx.lifecycle.LiveData
import com.example.madesubmission.core.domain.model.Details
import com.example.madesubmission.core.domain.model.Movie
import com.example.madesubmission.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {
    override fun getAllMovies(): Flow<List<Movie>> = movieRepository.getAllMovies()

    override fun getFavoriteMovies(): Flow<List<Movie>> = movieRepository.getFavoriteMovies()

    override fun checkFavoriteMovie(id: Int): LiveData<Boolean> = movieRepository.checkFavoriteMovie(id)

    override fun insertFavoriteMovie(movie: Movie) = movieRepository.insertMovie(movie)

    override fun deleteFavoriteMovie(movie: Movie) = movieRepository.deleteMovie(movie)

    override fun getMovieDetails(id: Int): Flow<Details> = movieRepository.getMovieDetails(id)

}