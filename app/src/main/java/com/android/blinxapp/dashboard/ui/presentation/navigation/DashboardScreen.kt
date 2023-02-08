package com.android.blinxapp.dashboard.ui.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.blinxapp.dashboard.ui.presentation.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {

    // TODO: Create NavController
    var navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    Scaffold(
        topBar = {
                DashboardTopBar()
        },
        bottomBar = {
            DashboardBottomNav(true, currentRoute, navController)
        }
    ) { innerPadding ->

        Box(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(top = 20.dp, start = 16.dp, end = 16.dp)) {
            DashboardNav(navController = navController)
        }
    }

}

@Composable
fun DashboardNav(navController: NavHostController) {
    NavHost(navController, startDestination = DashboardNavigationRoute.Home.name) {
        composable(DashboardNavigationRoute.Home.name) {
            HomeScreen()
        }
//        composable(DashboardNavigationRoute.Wallet.name) {
//            WalletCard()
//        }
//        composable(NavigationItem.Movies.route) {
//            MoviesScreen()
//        }
//        composable(NavigationItem.Books.route) {
//            BooksScreen()
//        }
//        composable(NavigationItem.Profile.route) {
//            ProfileScreen()
//        }
    }
}


@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen()
}