package ru.upsoft.gpspointer.domain.usecase

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import ru.upsoft.gpspointer.core.model.LocationFailure
import ru.upsoft.gpspointer.core.model.LocationState
import ru.upsoft.gpspointer.presentation.common.isGooglePlayServicesAvailable
import ru.upsoft.gpspointer.presentation.common.permissionIsGranted
import javax.inject.Inject

class LocationUseCaseImpl @Inject constructor(
    @ApplicationContext private val appContext: Context
) : LocationUseCase {

    override val locationStateFlow = MutableStateFlow<LocationState>(LocationState.Loading)

    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(appContext)
    }
    private val locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult ?: return
            val location = locationResult.lastLocation ?: return
            locationStateFlow.value = LocationState.LocationRetrieved(location)
        }
    }

    @SuppressLint("MissingPermission")
    override fun startLocationMonitoring() {
        if (!appContext.permissionIsGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
            locationStateFlow.value = LocationState.Failed(LocationFailure.PERMISSION_DENIED)
            return
        }
        if (!appContext.isGooglePlayServicesAvailable()) {
            locationStateFlow.value = LocationState.Failed(LocationFailure.GMS_UNAVAILABLE)
            return
        }
        fusedLocationClient.requestLocationUpdates(
            LocationRequest.create(),
            locationCallback,
            Looper.getMainLooper()
        )
    }

    override fun stopLocationMonitoring() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }
}