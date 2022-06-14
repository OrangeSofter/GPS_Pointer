package ru.upsoft.gpspointer.presentation.screens.points

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
    var inputText by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = {
            navController.popBackStack()
        },
        title = {
            Text(text = "Title")
        },
        text = {
            Column {
                TextField(
                    value = inputText,
                    onValueChange = { inputText = it }
                )
                Text("Custom Text")
                Checkbox(checked = false, onCheckedChange = {})
            }
        },
        buttons = {
            Row(
                modifier = Modifier.padding(all = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { navController.popBackStack() }
                ) {
                    Text("Dismiss")
                }
            }
        }

    )
}