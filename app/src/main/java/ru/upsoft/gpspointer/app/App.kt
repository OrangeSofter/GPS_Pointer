package ru.upsoft.gpspointer.app

import android.app.Application
import ru.upsoft.gpspointer.core.common.sendLog

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        sendLog("App onCreate")
    }

}