package ru.upsoft.gpspointer.presentation.ui.features.mainFunctionality

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import ru.upsoft.gpspointer.data.repository.WeatherRepository
import javax.inject.Inject

@HiltViewModel
class MainFunctionalityViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    val i = weatherRepository.i

}