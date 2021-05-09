package ru.upsoft.gpspointer.presentation.common

import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability

fun Context.isGooglePlayServicesAvailable(): Boolean {
    val api = GoogleApiAvailability.getInstance()
    return api.isGooglePlayServicesAvailable(this) == ConnectionResult.SUCCESS
}