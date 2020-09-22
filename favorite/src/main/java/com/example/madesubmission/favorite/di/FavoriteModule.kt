package com.example.madesubmission.favorite.di

import com.example.madesubmission.favorite.presentation.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}