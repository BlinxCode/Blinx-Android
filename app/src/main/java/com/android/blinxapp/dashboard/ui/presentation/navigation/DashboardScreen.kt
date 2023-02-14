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
import com.android.blinxapp.dashboard.ui.presentation.bvn.BvnConfirmationScreen
import com.android.blinxapp.dashboard.ui.presentation.bvn.BvnVerificationScreen
import com.android.blinxapp.dashboard.ui.presentation.home.HomeScreen
import com.android.blinxapp.dashboard.ui.presentation.wallet.FundWalletScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardNavigation() {

    // TODO: Create NavController
    var navController = rememberNavController()

    val canNavigateBack = remember { mutableStateOf(false) }
    val isDashboard = remember { mutableStateOf(false) }
    val topBarTitle = remember { mutableStateOf("") }
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Scaffold(
        topBar = { DashboardTopBar(canNavigateBack,topBarTitle, navigateUp = {}, isDashboard) },
        bottomBar = {
            DashboardBottomNav(isDashboard, currentRoute, navController)
        }
    ) { innerPadding ->

        Box(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding( start = 16.dp, end = 16.dp)) {
            DashboardNav(navController = navController,canNavigateBack = canNavigateBack,
                isDashboard = isDashboard, topBarTitle = topBarTitle)
        }
    }

}

@Composable
fun DashboardNav(
    navController: NavHostController,
    canNavigateBack: MutableState<Boolean>,
    isDashboard: MutableState<Boolean>,
    topBarTitle: MutableState<String>
) {
    NavHost(navController, startDestination = DashboardNavigationRoute.HOME.name) {
        composable(DashboardNavigationRoute.HOME.name) {
            isDashboard.value = true
            canNavigateBack.value = false

            HomeScreen(
                walletClick ={
                    navController.navigate(DashboardNavigationRoute.WALLET.name)
                },
                automationCardClick ={
                  //  navController.navigate(DashboardNavigationRoute.SET_AUTOMATION.name)
                },
                bvnCardClick ={
                    navController.navigate(DashboardNavigationRoute.BVN.name)
                },
                linkBankCardClick ={
                  //  navController.navigate(DashboardNavigationRoute.LINK_BANK.name)
                }
            )
        }
        composable(route = DashboardNavigationRoute.BVN.name) {
            isDashboard.value = false
            canNavigateBack.value = true
            topBarTitle.value = "Verify BVN"
            BvnVerificationScreen(
                onProceedClicked ={
                    navController.navigate(DashboardNavigationRoute.BVN_VALIDATE.name)
                }
            )
        }

        composable(route = DashboardNavigationRoute.BVN_VALIDATE.name) {
            isDashboard.value = false
            canNavigateBack.value = true
            topBarTitle.value = "Validate OTP"
            BvnConfirmationScreen(
                onProceedClicked ={
                 //   Success screen
                }
            )
        }

        composable(route = DashboardNavigationRoute.WALLET.name) {
            isDashboard.value = false
            canNavigateBack.value = true
            topBarTitle.value = "Fund Wallet"
            FundWalletScreen(
                onNairaClicked ={
                 //   Success screen
                },
                onDollarClicked ={
                 //   Success screen
                }
            )
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
    DashboardNavigation()
}