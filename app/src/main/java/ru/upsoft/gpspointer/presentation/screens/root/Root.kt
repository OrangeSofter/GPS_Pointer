package ru.upsoft.gpspointer.presentation.screens.root

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.upsoft.gpspointer.R
import ru.upsoft.gpspointer.domain.model.CompassState
import ru.upsoft.gpspointer.domain.model.Location
import ru.upsoft.gpspointer.domain.model.LocationState

@Preview
@Composable
fun RootScreen(
    viewModel: RootViewModel = viewModel()
) {
    DisposableEffect(key1 = viewModel) {
        viewModel.onStart()
        onDispose { viewModel.onStop() }
    }
    val locationState by viewModel.locationStateFlow.collectAsState()
    val compassState by viewModel.compassState.collectAsState()
    Surface(
        Modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Compass(compassState)
            when (locationState) {
                is LocationState.Failed -> Text("Ошибка")
                is LocationState.Loading -> CircularProgressIndicator()
                is LocationState.LocationRetrieved -> LocationLoaded((locationState as LocationState.LocationRetrieved).location)
            }
        }
    }
}

@Composable
fun Compass(state: CompassState) {
    when (state) {
        is CompassState.Loaded -> Surface {
            Image(
                painter = painterResource(id = R.drawable.compass_area),
                contentDescription = "Compass area",
                modifier = Modifier
                    .size(200.dp)
                    .rotate(state.degree)
            )
            Image(
                painter = painterResource(id = R.drawable.compass_arrow),
                contentDescription = "Compass arrow",
                modifier = Modifier.size(200.dp)
            )
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
fun PreviewLocationLoaded() {
    LocationLoaded(location = Location(37.0, 37.0))
}

@Composable
@Preview
fun PreviewCompass() {
    Compass(CompassState.Loaded(45f))
}