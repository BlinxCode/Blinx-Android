package com.android.blinxapp.ui.feature.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.blinxapp.R
import com.android.blinxapp.core.RequestState
import com.android.blinxapp.ui.feature.presentation.components.common.CommonTitle
import com.android.blinxapp.ui.feature.viewmodel.DashboardViewModel

@Composable
fun HomeScreen(
    displayName: String,
    walletClick: () -> Unit,
    automationCardClick: () -> Unit,
    bvnCardClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    // Collect the current state of plaidLinkUiState
    val plaidLinkUiState by viewModel.plaidLinkUiState.collectAsState()

    // Pass it to another function
    BankLinkingScreen(
        uiState = plaidLinkUiState
    )

    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            /**
             * Customers Greetings
             */
            CommonTitle("Hello,", displayName)

            /**
             * Add funds to Wallet
             */
            WalletCard(walletClick)
            /**
             * Create Automation service
             */
            ComposeCard(
                automationCardClick,
                painterResource(R.drawable.automate_icon),
                stringResource(R.string.no_automation),
                stringResource(R.string.create_automation)
            )

            /**
             * Add Your BVN
             */
            ComposeCard(
                bvnCardClick,
                painterResource(R.drawable.question_mark_icon),
                stringResource(R.string.add_bvn),
                stringResource(R.string.add_bvn_message)
            )

            /**
             * Link Bank Account
             */
            ComposeCard(
                onClicked = { viewModel.createPlaidLink() },
                painterResource(R.drawable.bank),
                stringResource(R.string.link_bank),
                stringResource(R.string.link_bank_message)
            )

            /**
             * Know Your Customer (KYC)
             */
//            DashboardCard(
//                painterResource(R.drawable.verified_user),
//                stringResource(R.string.title_kyc),
//                stringResource(R.string.message_kyc)
//            )

            /**
             * Spacer
             */
            Spacer(modifier = Modifier.size(40.dp))

        }

    }
}

@Composable
fun BankLinkingScreen(uiState: RequestState<String>) {
    when (uiState) {
        is RequestState.Loading -> {
            //  CircularProgressIndicator() // Show loading indicator

        }

        is RequestState.Success -> {
            val data = uiState.data
            Log.d("PlaidToken", "Plaid Link Created: $data")
            // Perform other success actions if needed
        }

        is RequestState.Error -> {
            val error = uiState.error
            Log.d("PlaidToken", "$error")
            // Handle error state
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenScaffoldPreview() {
    HomeScreen(
        displayName = "Oladotun",
        walletClick = {},
        automationCardClick = {},
        bvnCardClick = {},
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)

    )
}
