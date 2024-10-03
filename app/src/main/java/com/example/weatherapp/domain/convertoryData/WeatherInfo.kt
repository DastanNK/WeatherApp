package com.example.weatherapp.domain.convertoryData

data class WeatherInfo(
    val weatherDataPerDay: Map<Int, List<WeatherData>>,
    val currentWeather:WeatherData?
)
