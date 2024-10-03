package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.convertoryData.WeatherInfo


interface WeatherRepository {
    suspend fun getWeatherData(latitude:Double, longitude:Double) : WeatherInfo
}