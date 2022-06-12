package ru.upsoft.gpspointer.presentation.screens.points

import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ru.upsoft.gpspointer.domain.model.GeoPoint

@Composable
fun PointsScreen(
    viewModel: PointsViewModel = viewModel(),
    navController: NavController,
) {
    val points = viewModel.pointsStateFlow.collectAsState()
    Surface(
        Modifier.fillMaxSize(),
    ) {
        Text("Сохраненные точки")

        Surface(
            Modifier.absolutePadding(top = 16.dp)
        ) {
            if (points.value.isNotEmpty()) {
                LazyColumn {
                    items(points.value, key = { it.name }) { point ->
                        PointItem(point) {
                            navController.previousBackStackEntry?.savedStateHandle?.set("selectedPointName", point.name)
                            navController.popBackStack()
                        }
                    }
                }
            } else {
                Text("Нет сохраненных точек")
            }
        }

    }

}

@Composable
fun PointItem(point: GeoPoint, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(point.name)
    }
}