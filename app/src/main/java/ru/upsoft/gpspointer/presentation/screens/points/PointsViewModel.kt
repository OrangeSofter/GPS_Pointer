package ru.upsoft.gpspointer.presentation.screens.points

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.upsoft.gpspointer.domain.model.GeoPoint
import ru.upsoft.gpspointer.domain.usecase.navigation.NavigationUseCase
import javax.inject.Inject


@HiltViewModel
class PointsViewModel @Inject constructor(
    private val navigationUseCase: NavigationUseCase
) : ViewModel() {

    private val _pointsStateFlow = MutableStateFlow<List<GeoPoint>>(emptyList())
    val pointsStateFlow = _pointsStateFlow.asStateFlow()

    init {
        loadPoints()
    }

    fun loadPoints() = viewModelScope.launch {
        val points = navigationUseCase.loadPoints()
        _pointsStateFlow.value = points
    }

    fun deletePoint(pointName: String) = viewModelScope.launch {
        navigationUseCase.deletePoint(pointName)
    }
}