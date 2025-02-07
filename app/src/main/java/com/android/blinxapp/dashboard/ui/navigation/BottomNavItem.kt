package com.android.blinxapp.dashboard.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
)

val bottomNavItems = listOf(
    BottomNavItem(
        name = "Home",
        route = DashboardNavigationRoute.Home.name,
        icon = Icons.Rounded.Home,
    ),
    BottomNavItem(
        name = "Wallet",
        route = DashboardNavigationRoute.Wallet.name,
        icon = Icons.Rounded.AddCircle,
    ),
    BottomNavItem(
        name = "Settings",
        route = "",
        icon = Icons.Rounded.Settings,
    ),
)