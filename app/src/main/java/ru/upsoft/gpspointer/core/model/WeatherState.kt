package ru.upsoft.gpspointer.core.model

import ru.upsoft.gpspointer.data.network.model.WeatherNetworkModel

sealed class WeatherState {
    object Loading : WeatherState()
    object Failed : WeatherState()
    class Success(val data: WeatherNetworkModel) : WeatherState()//Todo: мапить данные в ui модель
}