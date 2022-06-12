package ru.upsoft.gpspointer.presentation.screens.points

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.upsoft.gpspointer.domain.model.GeoPoint
import ru.upsoft.gpspointer.domain.model.Location


class PointsViewModel : ViewModel() {

    val pointsStateFlow: StateFlow<List<GeoPoint>> = MutableStateFlow(
        listOf(
            GeoPoint("точка 1", Location(48.0, 48.0)),
            GeoPoint("точка 2", Location(49.0, 49.0)),
            GeoPoint("точка 3", Location(50.0, 51.0)),
        )
    )

    fun deletePoint(point: GeoPoint) {
        TODO()
    }
}