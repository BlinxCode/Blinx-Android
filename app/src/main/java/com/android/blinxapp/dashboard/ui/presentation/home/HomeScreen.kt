package com.android.blinxapp.dashboard.ui.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.blinxapp.R
import com.android.blinxapp.common.CommonTitle

@Composable
fun HomeScreen(
    walletClick: () -> Unit,
    automationCardClick: () -> Unit,
    bvnCardClick: () -> Unit,
    linkBankCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize().background(MaterialTheme.colorScheme.primary)

    .verticalScroll(scrollState)
            .background(MaterialTheme.colorScheme.primary)
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            /**
             * Customers Greetings
             */
            CommonTitle("Hello,", "Oladotun")

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
                stringResource(R.string.create_automation))

            /**
             * Add Your BVN
             */
            ComposeCard(
                bvnCardClick,
                painterResource(R.drawable.question_mark_icon),
                stringResource(R.string.add_bvn),
                stringResource(R.string.add_bvn_message))

            /**
             * Link Bank Account
             */
            ComposeCard(
                linkBankCardClick,
                painterResource(R.drawable.bank),
                stringResource(R.string.link_bank),
                stringResource(R.string.link_bank_message))

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
            Spacer(modifier = Modifier.size( 40.dp))

        }

    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenScaffoldPreview() {
    HomeScreen(walletClick = {},
        automationCardClick = {},
        bvnCardClick = {},
        linkBankCardClick = {},
        modifier = Modifier
            .padding( start = 16.dp, end = 16.dp)
    )
}
