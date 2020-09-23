package com.example.madesubmission.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madesubmission.core.data.source.local.LocalDataSource
import com.example.madesubmission.core.data.source.local.entity.IdEntity
import com.example.madesubmission.core.data.source.remote.RemoteDataSource
import com.example.madesubmission.core.domain.model.Movie
import com.example.madesubmission.core.domain.repository.IMovieRepository
import com.example.madesubmission.core.utils.DataMapper
import kotlinx.coroutines.flow.map

class MovieRepository(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource):
    IMovieRepository {

    override fun getAllMovies() = remoteDataSource.getAllMovies().map { DataMapper.mapResponsesToDomain(it) }

    override fun getFavoriteMovies() = localDataSource.getFavoriteMovies().map { DataMapper.mapEntitiesToDomain(it) }

    override fun checkFavoriteMovie(id: Int): LiveData<Boolean> {
        val liveData = MutableLiveData<Boolean>()
        localDataSource.checkFavoriteMovie(id, object : LocalDataSource.CheckFavoriteCallback{
            override fun onChecked(idEntity: IdEntity?) {
                if (idEntity?.id == id) liveData.postValue(true)
                else liveData.postValue(false)
            }
        })
        return liveData
    }

    override fun insertMovie(movie: Movie) = localDataSource.insertFavoriteMovie(DataMapper.mapDomainToEntity(movie))

    override fun deleteMovie(movie: Movie) = localDataSource.deleteFavoriteMovie(DataMapper.mapDomainToEntity(movie))

    override fun getMovieDetails(id: Int) = remoteDataSource.getMovieDetails(id).map { DataMapper.mapResponseToDomain(it) }

}