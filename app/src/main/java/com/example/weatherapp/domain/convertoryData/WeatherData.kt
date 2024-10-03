package com.example.weatherapp.domain.convertoryData

import com.example.weatherapp.domain.utils.WeatherType
import java.time.LocalDateTime


data class WeatherData(
    val time: LocalDateTime,
    val temperature:Double,
    val rain:Double,
    val windSpeed:Double,
    val weatherType: WeatherType
)
