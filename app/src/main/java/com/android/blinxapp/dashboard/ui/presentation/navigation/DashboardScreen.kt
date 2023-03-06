package com.android.blinxapp.dashboard.ui.presentation.navigation

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.android.blinxapp.dashboard.ui.presentation.bvn.BvnConfirmationScreen
import com.android.blinxapp.dashboard.ui.presentation.bvn.BvnVerificationScreen
import com.android.blinxapp.dashboard.ui.presentation.home.HomeScreen
import com.android.blinxapp.dashboard.ui.presentation.wallet.DebitCardInfoView
import com.android.blinxapp.dashboard.ui.presentation.wallet.FundNairaWallet
import com.android.blinxapp.dashboard.ui.presentation.wallet.FundWalletScreen
import com.android.blinxapp.dashboard.ui.presentation.wallet.FundWithBankTransferScreen

fun NavGraphBuilder.dashboardNavigation(
    navController: NavHostController,
    canNavigateBack: MutableState<Boolean>,
    isDashboard: MutableState<Boolean>,
    topBarTitle: MutableState<String>
) {
    composable(DashboardNavigationRoute.HOME.name) {
        isDashboard.value = true
        canNavigateBack.value = false

        HomeScreen(
            walletClick = {
                navController.navigate(DashboardNavigationRoute.WALLET.name)
            },
            automationCardClick = {
                //  navController.navigate(DashboardNavigationRoute.SET_AUTOMATION.name)
            },
            bvnCardClick = {
                navController.navigate(DashboardNavigationRoute.BVN.name)
            },
            linkBankCardClick = {
                //  navController.navigate(DashboardNavigationRoute.LINK_BANK.name)
            },
        )
    }

    composable(route = DashboardNavigationRoute.BVN.name) {
        isDashboard.value = false
        canNavigateBack.value = true
        topBarTitle.value = "Verify BVN"
        BvnVerificationScreen(
            onProceedClicked = {
                navController.navigate(DashboardNavigationRoute.BVN_VALIDATE.name)
            }
        )
    }

    composable(route = DashboardNavigationRoute.BVN_VALIDATE.name) {
        isDashboard.value = false
        canNavigateBack.value = true
        topBarTitle.value = "Validate OTP"
        BvnConfirmationScreen(
            onProceedClicked = {
                //   Success screen
            },

            )
    }

    composable(route = DashboardNavigationRoute.WALLET.name) {
        isDashboard.value = false
        canNavigateBack.value = true
        topBarTitle.value = "Fund Wallet"
        FundWalletScreen(
            onNairaClicked = {
                navController.navigate(DashboardNavigationRoute.FUND_WALLET.name)
            },
            onDollarClicked = {
                //   Success screen
            },

            )
    }
    composable(DashboardNavigationRoute.FUND_WALLET.name) {
        isDashboard.value = false
        canNavigateBack.value = true

        FundNairaWallet(
            onProceedClicked = {
                //   Success screen
            },
            onDebitCardClicked = {
                navController.navigate(DashboardNavigationRoute.ADD_DEBIT_CARD.name)
            },
            onBankTransferClicked = {
                navController.navigate(DashboardNavigationRoute.BANK_TRANSFER.name)
            }
        )
    }

    composable(DashboardNavigationRoute.ADD_DEBIT_CARD.name) {
        isDashboard.value = false
        canNavigateBack.value = true

        DebitCardInfoView {
            //   Success screen
        }
    }
    composable(DashboardNavigationRoute.BANK_TRANSFER.name) {
        isDashboard.value = false
        canNavigateBack.value = true

        FundWithBankTransferScreen(onProceedClicked = { /*TODO*/ })
        //   Success screen
    }

}
