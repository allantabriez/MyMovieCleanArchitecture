package com.example.madesubmission

import android.content.Context
import com.example.madesubmission.core.di.databaseModule
import com.example.madesubmission.core.di.networkModule
import com.example.madesubmission.core.di.recyclerViewModule
import com.example.madesubmission.core.di.repositoryModule
import com.example.madesubmission.di.useCaseModule
import com.example.madesubmission.di.viewModelModule
import com.google.android.play.core.splitcompat.SplitCompat
import com.google.android.play.core.splitcompat.SplitCompatApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : SplitCompatApplication() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                    recyclerViewModule
                )
            )
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        SplitCompat.install(this)
    }
}