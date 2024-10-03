package com.example.weatherapp.RetrieveData

import android.app.Application
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Provides
    @Singleton
    fun WeatherapiService():WeatherApi{
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create()).build().create()
    }
    @Provides
    @Singleton
    fun fusedLocationProviderClient(app:Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }
}