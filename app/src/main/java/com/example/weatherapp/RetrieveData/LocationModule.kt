package com.example.weatherapp.RetrieveData

import com.example.weatherapp.domain.convertoryData.LocationTrackerImpl
import com.example.weatherapp.domain.location.LocationTracker
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {
    @Binds
    @Singleton
    abstract fun bindLocationTracker(locationTrackerImpl: LocationTrackerImpl): LocationTracker
}