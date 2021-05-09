package ru.upsoft.gpspointer.core.common

import android.util.Log
import ru.upsoft.gpspointer.BuildConfig

private const val logTag = "GPS_Pointer log"

fun sendLog(message: String) {
    if (BuildConfig.DEBUG && message.isNotEmpty())
        Log.d(logTag, message)
}