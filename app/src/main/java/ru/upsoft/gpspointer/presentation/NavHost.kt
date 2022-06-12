package ru.upsoft.gpspointer.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.upsoft.gpspointer.presentation.screens.points.PointsScreen
import ru.upsoft.gpspointer.presentation.screens.points.PointsViewModel
import ru.upsoft.gpspointer.presentation.screens.root.RootScreen
import ru.upsoft.gpspointer.presentation.screens.root.RootViewModel

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.ROOT) {
        composable(Routes.ROOT) {
            val viewModel = hiltViewModel<RootViewModel>()
            RootScreen(viewModel, navController)
        }
        composable(Routes.POINTS) {
            val viewModel = hiltViewModel<PointsViewModel>()
            PointsScreen(viewModel)
        }
    }
}

object Routes {
    const val ROOT = "root"
    const val POINTS = "points"
}