package ru.upsoft.gpspointer.presentation.screens.points

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.upsoft.gpspointer.presentation.screens.savepoint.SavePointViewModel

@Composable
fun SafePointDialog(
    viewModel: SavePointViewModel,
    navController: NavController,
) {
    val saveCurrentLocationAvailable by viewModel.saveCurrentLocationAvailableState.collectAsState()
    val popBack by viewModel.popBackState.collectAsState()
    var inputName by remember { mutableStateOf("") }
    var inputCoordinates by remember { mutableStateOf("") }

    if (popBack) remember { navController.popBackStack() }
    AlertDialog(
        modifier = Modifier.fillMaxWidth(),
        onDismissRequest = {
            navController.popBackStack()
        },
        title = {
            Text(text = "Добавление новой точки")
            Spacer(modifier = Modifier.height(16.dp))
        },
        text = {
            Column {
                TextField(
                    value = inputName,
                    onValueChange = { inputName = it }
                )
                Text("Введите имя точки")
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = inputCoordinates,
                    onValueChange = { inputCoordinates = it }
                )
                Text("Введите координаты точки")
            }
        },
        buttons = {
            Row(
                modifier = Modifier.padding(all = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.5F)
                        .height(56.dp),
                    enabled = viewModel.inputCoordinatesIsCorrect(inputCoordinates) && inputName.isNotBlank(),
                    onClick = { viewModel.savePoint(inputName, inputCoordinates) }
                ) {
                    Text("Сохранить")
                }
                if (saveCurrentLocationAvailable && inputName.isNotBlank()) {
                    Spacer(modifier = Modifier.width(2.dp))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        onClick = { viewModel.savePointByCurrentLocation(inputName) }
                    ) {
                        Text("Сохранить по текущему положению")
                    }
                }
            }
        }

    )
}