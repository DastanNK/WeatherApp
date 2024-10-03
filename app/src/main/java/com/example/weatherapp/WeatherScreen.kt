package com.example.weatherapp

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.weatherapp.domain.convertoryData.WeatherInfo
import com.example.weatherapp.domain.convertoryData.WeatherState

@Composable
fun WeatherScreen(
    state:WeatherState
){
    Text(state.weatherInfo.toString())
}