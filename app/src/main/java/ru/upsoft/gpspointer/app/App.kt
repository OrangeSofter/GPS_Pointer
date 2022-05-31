package ru.upsoft.gpspointer.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import ru.upsoft.gpspointer.common.sendLog

@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        sendLog("App onCreate")
    }

}