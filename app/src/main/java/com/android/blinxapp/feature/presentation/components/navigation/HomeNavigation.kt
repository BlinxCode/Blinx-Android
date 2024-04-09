package com.android.blinxapp.feature.presentation.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.android.blinxapp.feature.presentation.bvn.BvnConfirmationScreen
import com.android.blinxapp.feature.presentation.bvn.BvnVerificationScreen
import com.android.blinxapp.feature.presentation.components.common.paddingValues
import com.android.blinxapp.feature.presentation.home.HomeScreen
import com.android.blinxapp.feature.presentation.wallet.DebitCardInfoView
import com.android.blinxapp.feature.presentation.wallet.FundNairaWallet
import com.android.blinxapp.feature.presentation.wallet.FundWalletScreen
import com.android.blinxapp.feature.presentation.wallet.FundWithBankTransferScreen


@Composable
fun DashBoardNavigation(navController: NavHostController){
    // Retrieve the application context
    val canNavigateBack = remember { mutableStateOf(false) }
    val isDashboard = remember { mutableStateOf(true) }
    val topBarTitle = remember { mutableStateOf("") }

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val context = remember { navController.context }
    // TODO: Create NavController

    val modifier: Modifier = Modifier

    val destinationStr = remember { mutableStateOf(HomeNavigationRoute.HOME.name) }

    // TODO: Get current back stack entry

    // TODO: Get the name of the current screen
    // TODO: add NavHost




    Scaffold(
        topBar = {
            DashboardTopBar( canNavigateBack.value,
                topBarTitle, navigateUp = {},
                isDashboard.value,
                isGettingStarted = false,)
        },
        bottomBar = {
            if (isDashboard.value){
                DashboardBottomNav(isDashboard, currentRoute, navController)
            }
        }
    ){ innerPadding ->

        Box(modifier = modifier.fillMaxSize()
            .padding(innerPadding)
            .background(MaterialTheme.colorScheme.primary)){
            Box(modifier = modifier.padding(paddingValues())
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)) {

                DashboardNavigation(navController, canNavigateBack,
                    isDashboard, topBarTitle,destinationStr )
            }
        }

    }

}
@Composable
fun DashboardNavigation(
    navController: NavHostController,
    canNavigateBack: MutableState<Boolean>,
    isDashboard: MutableState<Boolean>,
    topBarTitle: MutableState<String>,
    destinationStr: MutableState<String>
) {

    NavHost(
        navController = navController,
        startDestination = destinationStr.value
    ) {
        composable(HomeNavigationRoute.HOME.name) {
            isDashboard.value = true
            canNavigateBack.value = false
            HomeScreen(
                walletClick = {
                    navController.navigate(HomeNavigationRoute.WALLET.name)
                },
                automationCardClick = {
                    //  navController.navigate(DashboardNavigationRoute.SET_AUTOMATION.name)
                },
                bvnCardClick = {
                    navController.navigate(HomeNavigationRoute.BVN.name)
                },
                linkBankCardClick = {
                    //  navController.navigate(DashboardNavigationRoute.LINK_BANK.name)
                },
            )
        }


        composable(route = HomeNavigationRoute.BVN.name) {
            isDashboard.value = false
            canNavigateBack.value = true
            topBarTitle.value = "Verify BVN"
            BvnVerificationScreen(
                onProceedClicked = {
                    navController.navigate(HomeNavigationRoute.BVN_VALIDATE.name)
                }
            )
        }

        composable(route = HomeNavigationRoute.BVN_VALIDATE.name) {
            isDashboard.value = false
            canNavigateBack.value = true
            topBarTitle.value = "Validate OTP"
            BvnConfirmationScreen(
                onProceedClicked = {
                    //   Success screen
                },

                )
        }

        composable(route = HomeNavigationRoute.WALLET.name) {
            isDashboard.value = false
            canNavigateBack.value = true
            topBarTitle.value = "Fund Wallet"
            FundWalletScreen(
                onNairaClicked = {
                    navController.navigate(HomeNavigationRoute.FUND_WALLET.name)
                },
                onDollarClicked = {
                    //   Success screen
                },

                )
        }
        composable(HomeNavigationRoute.FUND_WALLET.name) {
            isDashboard.value = false
            canNavigateBack.value = true

            FundNairaWallet(
                onProceedClicked = {
                    //   Success screen
                },
                onDebitCardClicked = {
                    navController.navigate(HomeNavigationRoute.ADD_DEBIT_CARD.name)
                },
                onBankTransferClicked = {
                    navController.navigate(HomeNavigationRoute.BANK_TRANSFER.name)
                }
            )
        }

        composable(HomeNavigationRoute.ADD_DEBIT_CARD.name) {
            isDashboard.value = false
            canNavigateBack.value = true

            DebitCardInfoView {
                //   Success screen
            }
        }
        composable(HomeNavigationRoute.BANK_TRANSFER.name) {
            isDashboard.value = false
            canNavigateBack.value = true

            FundWithBankTransferScreen(onProceedClicked = { /*TODO*/ })
            //   Success screen
        }
    }
}

