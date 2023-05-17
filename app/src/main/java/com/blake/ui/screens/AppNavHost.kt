package com.blake.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.blake.ui.Screen

@ExperimentalMaterial3Api
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Screen.DriverScreen.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.DriverScreen.route) {
            DriverScreen { driverId ->
                navController.navigate("route/${driverId}")
            }
        }
        composable(Screen.RouteScreen.route) {
            RouteScreen()
        }
    }
}