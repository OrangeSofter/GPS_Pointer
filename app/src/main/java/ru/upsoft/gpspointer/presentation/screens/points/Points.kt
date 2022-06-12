package ru.upsoft.gpspointer.presentation.screens.points

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
@Preview
fun PointsScreen(
    viewModel: PointsViewModel = viewModel()
) {
    Surface(
        Modifier.fillMaxSize(),
    ) {
        Text("Сохраненные точки")
    }

}