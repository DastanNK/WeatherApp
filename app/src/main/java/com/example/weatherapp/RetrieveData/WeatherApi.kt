package com.example.weatherapp.RetrieveData

import com.example.weatherapp.data.HourlyDto
import com.example.weatherapp.data.WeatherForecastDto
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://api.open-meteo.com"

interface WeatherApi {
    @GET("/v1/forecast?&hourly=temperature_2m,rain,wind_speed_10m,weathercode")
    suspend fun getApi(@Query("latitude") latitude:Double, @Query("longitude") longitude:Double): WeatherForecastDto
}