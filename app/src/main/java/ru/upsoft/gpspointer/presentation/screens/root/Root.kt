package ru.upsoft.gpspointer.presentation.screens.root

import android.location.Location
import androidx.compose.foundation.layout.Column
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.upsoft.gpspointer.core.model.LocationState

@Preview
@Composable
fun RootScreen(
    viewModel: RootViewModel = viewModel()
) {
    val viewState by viewModel.locationStateFlow.collectAsState()
    when (viewState) {
        is LocationState.Failed -> Text("Ошибка")
        LocationState.Loading -> CircularProgressIndicator()
        is LocationState.LocationRetrieved -> LocationLoaded((viewState as LocationState.LocationRetrieved).location)
    }
}

@Composable
fun LocationLoaded(location: Location){
    Surface() {
        Column {
            Text("Широта: ${location.latitude}")
            Text("Долгота: ${location.longitude}")
        }
    }

}