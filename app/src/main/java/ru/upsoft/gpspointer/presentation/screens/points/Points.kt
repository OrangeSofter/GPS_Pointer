package ru.upsoft.gpspointer.presentation.screens.points

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.upsoft.gpspointer.domain.model.GeoPoint
import ru.upsoft.gpspointer.presentation.Routes

@Composable
fun PointsScreen(
    viewModel: PointsViewModel,
    navController: NavController,
) {
    val points = viewModel.pointsStateFlow.collectAsState()
    remember { viewModel.loadPoints() }// Todo: Загрузка по возвращению с диалога
    Column(
        Modifier.fillMaxSize(),
    ) {
        Text("Сохраненные точки", fontSize = 24.sp)
        Surface(
            Modifier.absolutePadding(top = 16.dp)
        ) {
            if (points.value.isNotEmpty()) {
                LazyColumn {
                    items(points.value, key = { it.name }) { point ->
                        PointItem(point) {
                            navController.previousBackStackEntry?.savedStateHandle?.set(
                                "selectedPointName",
                                point.name
                            )
                            navController.popBackStack()
                        }
                    }
                }
            } else {
                Text("Нет сохраненных точек")
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { navController.navigate(Routes.SAVE_POINT_DIALOG) }) {
            Text("Сохранить точку", fontSize = 20.sp)
        }

    }

}

@Composable
fun PointItem(point: GeoPoint, onClick: () -> Unit) {
    Surface(
        Modifier
            .clickable { onClick() }
            .padding(4.dp),
        color = MaterialTheme.colors.primaryVariant

    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
            text = AnnotatedString(point.name),
            style = TextStyle(fontSize = 24.sp)
        )
    }
}