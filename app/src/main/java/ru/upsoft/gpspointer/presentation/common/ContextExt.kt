package ru.upsoft.gpspointer.presentation.common

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability

fun Context.isGooglePlayServicesAvailable(): Boolean {
    val api = GoogleApiAvailability.getInstance()
    return api.isGooglePlayServicesAvailable(this) == ConnectionResult.SUCCESS
}

fun Context.permissionIsGranted(permissionName: String): Boolean =
    ContextCompat.checkSelfPermission(
        this,
        permissionName
    ) == PackageManager.PERMISSION_GRANTED