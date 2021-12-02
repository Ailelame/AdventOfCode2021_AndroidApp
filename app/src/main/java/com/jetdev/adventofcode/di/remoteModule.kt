package com.jetdev.adventofcode.di

import com.jetdev.adventofcode.remote.CodeDownloaderManager
import com.jetdev.adventofcode.remote.GistEndpoint
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory


val remoteModule = module {
    single {

        OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build() }
    single {
        Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl("https://raw.githubusercontent.com/Ailelame/")
            .client(get())
            .build()

    }
    factory { get<Retrofit>().create(GistEndpoint::class.java) }
    factory { CodeDownloaderManager(get()) }
}

