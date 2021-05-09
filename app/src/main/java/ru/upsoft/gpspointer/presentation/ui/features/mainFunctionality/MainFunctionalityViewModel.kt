package ru.upsoft.gpspointer.presentation.ui.features.mainFunctionality

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.upsoft.gpspointer.data.repository.WeatherRepository
import ru.upsoft.gpspointer.data.repository.WeatherRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class MainFunctionalityViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
) : ViewModel() {

    val i = weatherRepository.i

}