package com.example.weatherapp.domain.convertoryData

data class WeatherState(
    val weatherInfo: WeatherInfo?=null,
    val isLoading: Boolean = false,
    val Error:String?=null
)
