package com.example.weatherapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.convertoryData.WeatherState
import com.example.weatherapp.domain.location.LocationTracker
import com.example.weatherapp.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
) : ViewModel()  {
    var state = mutableStateOf(WeatherState())
        private set
    fun loadWeatherInfo(){
        viewModelScope.launch {
            state.value=state.value.copy(
                isLoading = true,
                Error = null
            )
            locationTracker.getLocation()?.let {Location->
                repository.getWeatherData(latitude = Location.latitude,longitude = Location.longitude)
            }

        }
    }
}