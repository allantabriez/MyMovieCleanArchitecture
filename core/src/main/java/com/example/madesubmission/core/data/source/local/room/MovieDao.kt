package com.example.madesubmission.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.madesubmission.core.data.source.local.entity.IdEntity
import com.example.madesubmission.core.data.source.local.entity.MovieEntity
import com.example.madesubmission.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(entity = MovieEntity::class, onConflict = OnConflictStrategy.IGNORE)
    fun insertMovie(favoriteMovies: MovieEntity)

    @Query("SELECT * from movie_table")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * from movie_table WHERE id LIKE :searchID")
    fun checkFavoriteMovie(searchID: Int): IdEntity?

    @Delete(entity = MovieEntity::class)
    fun deleteMovie(favoriteMovies: MovieEntity)
}