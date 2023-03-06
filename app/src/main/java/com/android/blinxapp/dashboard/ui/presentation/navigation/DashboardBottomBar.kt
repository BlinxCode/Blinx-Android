package com.android.blinxapp.dashboard.ui.presentation.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavHostController
import com.android.blinxapp.R

@Composable
fun DashboardBottomNav(
    isDashboard: MutableState<Boolean>,
    currentRoute: String?,
    navController: NavHostController
) {

    if (!isDashboard.value) {
        return
    }
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
                icon = {
                    BottomIcons(item)
                }
            )
        }
    }
}

@Composable
private fun BottomIcons(item: DashboardBottomNavItem) {
    Icon(
        imageVector = when (item.name) {
            "Automate" -> {
                ImageVector.vectorResource(id = R.drawable.automate_module)
            }
            "Settings" -> {
                ImageVector.vectorResource(id = R.drawable.settings)
            }
            else -> {
                item.icon
            }
        },
        contentDescription = "${item.name} Icon",
    )
}
