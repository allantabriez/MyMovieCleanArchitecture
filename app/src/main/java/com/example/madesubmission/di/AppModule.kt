package com.example.madesubmission.di

import com.example.madesubmission.core.domain.repository.MovieInteractor
import com.example.madesubmission.core.domain.usecase.MovieUseCase
import com.example.madesubmission.presentation.viewmodel.DetailViewModel
import com.example.madesubmission.presentation.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}