package ru.upsoft.gpspointer.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.upsoft.gpspointer.presentation.screens.points.PointsScreen
import ru.upsoft.gpspointer.presentation.screens.root.RootScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "${Routes.ROOT}?selectedPoint={selectedPoint}") {
        composable(
            "${Routes.ROOT}?selectedPoint={selectedPoint}",
            arguments = listOf(navArgument("selectedPoint") {
                type = NavType.StringType
                nullable = true
            }
            ),
        ) { backStackEntry ->
            RootScreen(
                viewModel = hiltViewModel(),
                selectedPointName = backStackEntry.arguments?.getString("selectedPoint"),
                navController = navController,
            )
        }
        composable(Routes.POINTS) {
            PointsScreen(
                viewModel = hiltViewModel(),
                navController = navController,
            )
        }
    }
}

object Routes {
    const val ROOT = "root"
    const val POINTS = "points"
}