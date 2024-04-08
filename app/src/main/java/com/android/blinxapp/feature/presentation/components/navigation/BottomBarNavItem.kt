package com.android.blinxapp.feature.presentation.components.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class DashboardBottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
)

val bottomNavItems = listOf(
    DashboardBottomNavItem(
        name = "Home",
        route = HomeNavigationRoute.HOME.name,
        icon = Icons.Rounded.Home,
    ),
    DashboardBottomNavItem(
        name = "Automate",
        route = HomeNavigationRoute.WALLET.name,
        icon = Icons.Rounded.AddCircle,
    ),
    DashboardBottomNavItem(
        name = "Settings",
        route = "",
        icon = Icons.Rounded.Settings,
    ),
)