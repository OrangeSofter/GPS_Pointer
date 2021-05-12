package ru.upsoft.gpspointer.presentation.ui.features.mainFunctionality

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.upsoft.gpspointer.data.repository.WeatherRepository
import ru.upsoft.gpspointer.domain.usecase.location.LocationUseCase
import javax.inject.Inject

@HiltViewModel
class MainFunctionalityViewModel @Inject constructor(
    private val locationUseCase: LocationUseCase,
    private val weatherRepository: WeatherRepository,
) : ViewModel() {

    val locationStateFlow = locationUseCase.locationStateFlow

    fun startLocationMonitoring() = viewModelScope.launch {
        locationUseCase.startLocationMonitoring()
    }

    fun stopLocationMonitoring() {
        locationUseCase.stopLocationMonitoring()
    }

}