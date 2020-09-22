package com.example.madesubmission.favorite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.madesubmission.core.domain.usecase.MovieUseCase

class FavoriteViewModel(private val movieUseCase: MovieUseCase): ViewModel() {
    val favorites = movieUseCase.getFavoriteMovies().asLiveData()
}