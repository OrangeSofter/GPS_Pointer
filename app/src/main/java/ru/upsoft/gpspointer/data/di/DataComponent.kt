package ru.upsoft.gpspointer.data.di

import ru.upsoft.gpspointer.data.repository.WeatherRepository

interface DataComponent {

    fun inject(weatherRepository: WeatherRepository)

}