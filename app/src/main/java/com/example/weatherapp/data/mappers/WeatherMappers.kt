package com.example.weatherapp.data.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.weatherapp.data.HourlyDto
import com.example.weatherapp.data.WeatherForecastDto
import com.example.weatherapp.domain.convertoryData.WeatherData
import com.example.weatherapp.domain.convertoryData.WeatherInfo
import com.example.weatherapp.domain.utils.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(val index: Int, val data: WeatherData)

@RequiresApi(Build.VERSION_CODES.O)
fun HourlyDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val newTemp = temperature[index]
        val newRain = rain[index]
        val newWind = windSpeed[index]
        val newType = WeatherType.fromWMO(code[index])
        val newTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME)

        IndexedWeatherData(
            index, WeatherData(
                newTime,
                newTemp,
                newRain,
                newWind,
                newType
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { it.data }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherForecastDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = hourly.toWeatherDataMap()
    val now=LocalDateTime.now()
    val currentWeatherInfo= weatherDataMap[0]?.find{
        val hour=if(now.minute<30) now.hour else now.hour+1
        it.time.hour==hour
    }
    return WeatherInfo(weatherDataMap, currentWeatherInfo)
}