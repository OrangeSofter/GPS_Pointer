package ru.upsoft.gpspointer.presentation.screens.points

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.upsoft.gpspointer.domain.usecase.navigation.NavigationUseCase
import javax.inject.Inject


@HiltViewModel
class PointsViewModel @Inject constructor(
    private val navigationUseCase: NavigationUseCase
) : ViewModel() {

    val pointsStateFlow = navigationUseCase.pointsState

    fun loadPoints() = viewModelScope.launch {
        navigationUseCase.loadPoints()
    }

    fun deletePoint(pointName: String) = viewModelScope.launch {
        navigationUseCase.deletePoint(pointName)
    }
}