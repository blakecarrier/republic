package com.blake.ui

sealed class Screen(val route: String) {
    object DriverScreen : Screen("driver")
    object RouteScreen : Screen("route/{driverId}")
}