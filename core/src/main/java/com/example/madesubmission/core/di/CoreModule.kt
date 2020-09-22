package com.example.madesubmission.core.di

import androidx.room.Room
import com.example.madesubmission.core.data.MovieRepository
import com.example.madesubmission.core.data.source.local.LocalDataSource
import com.example.madesubmission.core.data.source.local.room.MovieDatabase
import com.example.madesubmission.core.data.source.remote.RemoteDataSource
import com.example.madesubmission.core.data.source.remote.network.ApiService
import com.example.madesubmission.core.domain.repository.IMovieRepository
import com.example.madesubmission.core.ui.RecyclerViewAdapter
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java,
            "Movie.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}

val networkModule = module {
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IMovieRepository> {
        MovieRepository(
            get(),
            get()
        )
    }
}

val recyclerViewModule = module {
    factory { RecyclerViewAdapter() }
}