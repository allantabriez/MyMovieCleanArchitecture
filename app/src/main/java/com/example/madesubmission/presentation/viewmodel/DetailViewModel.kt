package com.example.madesubmission.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.madesubmission.core.domain.model.Details
import com.example.madesubmission.core.domain.model.Movie
import com.example.madesubmission.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase): ViewModel() {
    fun getDetails(id: Int): LiveData<Details> =  movieUseCase.getMovieDetails(id).asLiveData()
    fun checkFavorite(id: Int): LiveData<Boolean> = movieUseCase.checkFavoriteMovie(id)
    fun insertFavoriteMovie(movie: Movie) = movieUseCase.insertFavoriteMovie(movie)
    fun deleteFavoriteMovie(movie: Movie) = movieUseCase.deleteFavoriteMovie(movie)
}