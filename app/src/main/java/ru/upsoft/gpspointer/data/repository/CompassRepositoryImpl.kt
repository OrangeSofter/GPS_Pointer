package ru.upsoft.gpspointer.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.upsoft.gpspointer.domain.model.CompassState
import ru.upsoft.gpspointer.domain.repository.CompassRepository
import javax.inject.Inject

class CompassRepositoryImpl @Inject constructor(

) : CompassRepository {
    private val _compassStateFlow = MutableStateFlow(CompassState.Loaded(0f))

    override val compassStateFlow: StateFlow<CompassState> = _compassStateFlow.asStateFlow()

    override fun startCompass() {

    }

    override fun stopCompass() {

    }
}