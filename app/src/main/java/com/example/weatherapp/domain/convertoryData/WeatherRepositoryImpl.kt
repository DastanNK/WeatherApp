package com.example.weatherapp.domain.convertoryData

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.weatherapp.RetrieveData.WeatherApi
import com.example.weatherapp.data.mappers.toWeatherInfo
import com.example.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val api:WeatherApi): WeatherRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeatherData(latitude: Double, longitude: Double): WeatherInfo {
        return api.getApi(
            latitude = latitude,
            longitude = longitude
        ).toWeatherInfo()
    }
}