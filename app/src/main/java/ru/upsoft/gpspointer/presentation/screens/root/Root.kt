package ru.upsoft.gpspointer.presentation.screens.root

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
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
import androidx.navigation.NavController
import ru.upsoft.gpspointer.R
import ru.upsoft.gpspointer.domain.model.CompassState
import ru.upsoft.gpspointer.domain.model.Location
import ru.upsoft.gpspointer.domain.model.LocationState
import ru.upsoft.gpspointer.domain.model.SelectedPointState
import ru.upsoft.gpspointer.presentation.Routes

@Composable
fun RootScreen(
    viewModel: RootViewModel,
    navController: NavController,
) {
    DisposableEffect(viewModel) {
        viewModel.onStart()
        onDispose { viewModel.onStop() }
    }
    val selectedPointName: String? = navController.currentBackStackEntry?.savedStateHandle?.get("selectedPointName")
    DisposableEffect(selectedPointName) {
        viewModel.onSelectPoint(selectedPointName)
        onDispose { }
    }
    val locationState by viewModel.locationStateFlow.collectAsState()
    val compassState by viewModel.compassState.collectAsState()
    val selectedPointState by viewModel.selectedPointState.collectAsState()
    Surface(
        Modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Compass(compassState)
            selectedPointState?.let { SelectedPoint(it) }
            when (locationState) {
                is LocationState.Failed -> Text("Ошибка")
                is LocationState.Loading -> CircularProgressIndicator()
                is LocationState.LocationRetrieved -> LocationLoaded((locationState as LocationState.LocationRetrieved).location)
            }
            Button(
                onClick = {
                    navController.navigate(Routes.POINTS)
                }
            ) { Text("К точкам") }
        }
    }
}

@Composable
fun Compass(state: CompassState) {
    when (state) {
        is CompassState.Loaded -> Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface {
                Image(
                    painter = painterResource(id = R.drawable.compass_area),
                    contentDescription = "Compass area",
                    modifier = Modifier
                        .size(200.dp)
                        .rotate(-state.degree)
                )
                Image(
                    painter = painterResource(id = R.drawable.compass_arrow),
                    contentDescription = "Compass arrow",
                    modifier = Modifier.size(200.dp)
                )
            }
            Text("${state.degree.toInt()}")
        }
        is CompassState.Failed -> {}
        is CompassState.Initial -> {}
    }
}

@Composable
fun SelectedPoint(state: SelectedPointState) {
    Column {
        Image(
            painter = painterResource(id = R.drawable.point_arrow),
            contentDescription = "Compass area",
            modifier = Modifier
                .size(50.dp)
                .rotate(state.degreeToPoint)
        )
        Text(state.selectedPoint.name, color = MaterialTheme.colors.primary)
        Text("${state.kilometersToPoint}км", color = MaterialTheme.colors.primary)
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