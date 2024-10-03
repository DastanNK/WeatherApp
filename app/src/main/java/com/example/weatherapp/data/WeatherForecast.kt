package com.example.weatherapp.data

import com.squareup.moshi.Json

data class WeatherForecastDto(
    @field:Json(name="hourly")
    val hourly:HourlyDto
)


data class HourlyDto(
    @field:Json(name = "time")
    val time:List<String>,
    @field:Json(name = "temperature_2m")
    val temperature:List<Double>,
    @field:Json(name = "rain")
    val rain:List<Double>,
    @field:Json(name = "wind_speed_10m")
    val windSpeed:List<Double>,
    @field:Json(name = "weathercode")
    val code:List<Int>
)

