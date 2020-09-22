package com.example.madesubmission.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "releaseDate")
    var releaseDate: String?,
    @ColumnInfo(name = "overView")
    var overView: String?,
    @ColumnInfo(name = "imagePath")
    var imagePath: String,
    @ColumnInfo(name = "voteAverage")
    var voteAverage: Double,
    @ColumnInfo(name = "voteCount")
    var voteCount: Int,
    @ColumnInfo(name = "popularity")
    var popularity: Double
)