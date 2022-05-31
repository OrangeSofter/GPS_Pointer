package ru.upsoft.gpspointer.data.repository

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import com.google.android.gms.location.*
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import ru.upsoft.gpspointer.core.model.Location
import ru.upsoft.gpspointer.core.model.LocationFailure
import ru.upsoft.gpspointer.core.model.LocationState
import ru.upsoft.gpspointer.domain.repository.LocationRepository
import ru.upsoft.gpspointer.presentation.common.isGooglePlayServicesAvailable
import ru.upsoft.gpspointer.presentation.common.permissionIsGranted
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    @ApplicationContext private val appContext: Context
) : LocationRepository {
    override val locationStateFlow = MutableStateFlow<LocationState>(LocationState.Loading)

    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(appContext)
    }
    private val locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val location = locationResult.lastLocation
            locationStateFlow.value = LocationState.LocationRetrieved(Location(location.latitude, location.longitude))
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
        fusedLocationClient.requestLocationUpdates(//Todo: Ускорить определение местоположения + лупер на фоновом потоке
            LocationRequest.create(),
            locationCallback,
            Looper.getMainLooper()
        )
    }

    override fun stopLocationMonitoring() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }
}