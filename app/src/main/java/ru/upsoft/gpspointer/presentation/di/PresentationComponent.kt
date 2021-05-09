package ru.upsoft.gpspointer.presentation.di

import ru.upsoft.gpspointer.data.repository.WeatherRepository
import ru.upsoft.gpspointer.presentation.ui.features.mainFunctionality.MainFunctionalityViewModel

interface PresentationComponent {

    fun inject(mainFunctionalityViewModel: MainFunctionalityViewModel)

}