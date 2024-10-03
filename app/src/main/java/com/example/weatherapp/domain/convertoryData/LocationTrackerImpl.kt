package com.example.weatherapp.domain.convertoryData

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.example.weatherapp.domain.location.LocationTracker
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class LocationTrackerImpl @Inject  constructor(
    private val app: Application,
    private val locationClient: FusedLocationProviderClient

) : LocationTracker {
    override suspend fun getLocation(): Location? {
        val accessFineLocation = ContextCompat.checkSelfPermission(
            app,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val accessCoarseLocation = ContextCompat.checkSelfPermission(
            app,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val locationManager = app.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled =
            locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
            )
        if (accessFineLocation && accessCoarseLocation && isGpsEnabled) {
            return suspendCancellableCoroutine {continuation ->
                locationClient.lastLocation.apply {
                    if (isComplete) {
                        if(isSuccessful) {
                            continuation.resume(result)
                        }else{
                            continuation.resume(null)
                        }
                        return@suspendCancellableCoroutine
                    }
                    addOnSuccessListener {
                        continuation.resume(it)
                    }
                    addOnFailureListener {
                        continuation.resume(null)
                    }
                    addOnCanceledListener {
                        continuation.cancel()
                    }
                }
            }
        }else{
            return null
        }
    }
}