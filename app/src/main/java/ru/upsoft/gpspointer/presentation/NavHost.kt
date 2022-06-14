package ru.upsoft.gpspointer.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import ru.upsoft.gpspointer.presentation.screens.points.PointsScreen
import ru.upsoft.gpspointer.presentation.screens.points.SafePointDialog
import ru.upsoft.gpspointer.presentation.screens.root.RootScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.ROOT) {
        composable(Routes.ROOT) {
            RootScreen(
                viewModel = hiltViewModel(),
                navController = navController,
            )
        }
        composable(Routes.POINTS) {
            PointsScreen(
                viewModel = hiltViewModel(),
                navController = navController,
            )
        }
        dialog(Routes.SAVE_POINT_DIALOG) {
            SafePointDialog(
                viewModel = hiltViewModel(),
                navController = navController,
            )
        }
    }
}

object Routes {
    const val ROOT = "root"
    const val POINTS = "points"
    const val SAVE_POINT_DIALOG = "savePoint"
}