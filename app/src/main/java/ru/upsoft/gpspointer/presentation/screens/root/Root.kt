package ru.upsoft.gpspointer.presentation.screens.root

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.upsoft.gpspointer.core.model.Location
import ru.upsoft.gpspointer.core.model.LocationState

@Preview
@Composable
fun RootScreen(
    viewModel: RootViewModel = viewModel()
) {
    DisposableEffect(key1 = viewModel) {
        viewModel.onStart()
        onDispose { viewModel.onStop() }
    }
    val viewState by viewModel.locationStateFlow.collectAsState()
    Surface(
        Modifier
            .fillMaxSize()
    ) {
        when (viewState) {
            is LocationState.Failed -> Text("Ошибка")
            is LocationState.Loading -> CircularProgressIndicator()
            is LocationState.LocationRetrieved -> LocationLoaded((viewState as LocationState.LocationRetrieved).location)
        }
    }
}

@Composable
fun LocationLoaded(location: Location) {
    Column(
        //Modifier.background(Color.Yellow)
    ) {
        Text("Широта: ${location.latitude}")
        Text("Долгота: ${location.longitude}")
    }
}

@Composable
@Preview
fun PreviewLocationLoaded(){
    LocationLoaded(location = Location(37.0, 37.0))
}