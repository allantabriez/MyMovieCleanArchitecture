package com.example.madesubmission.core.data.source.local

import com.example.madesubmission.core.data.source.local.entity.IdEntity
import com.example.madesubmission.core.data.source.local.entity.MovieEntity
import com.example.madesubmission.core.data.source.local.room.MovieDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocalDataSource(private val movieDao: MovieDao) {

    fun getFavoriteMovies() = movieDao.getFavoriteMovies()

    fun checkFavoriteMovie(id: Int, callback: CheckFavoriteCallback) {
        CoroutineScope(Dispatchers.IO).launch {
            callback.onChecked(movieDao.checkFavoriteMovie(id))
        }
    }

    fun insertFavoriteMovie(movieEntity: MovieEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            movieDao.insertMovie(movieEntity)
        }
    }

    fun deleteFavoriteMovie(movieEntity: MovieEntity){
        CoroutineScope(Dispatchers.IO).launch {
            movieDao.deleteMovie(movieEntity)
        }
    }

    interface CheckFavoriteCallback {
        fun onChecked(idEntity: IdEntity?)
    }
}