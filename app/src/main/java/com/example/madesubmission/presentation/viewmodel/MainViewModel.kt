package com.example.madesubmission.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.madesubmission.core.domain.usecase.MovieUseCase

class MainViewModel(private val movieUseCase: MovieUseCase): ViewModel() {
    val movies = movieUseCase.getAllMovies().asLiveData()
}