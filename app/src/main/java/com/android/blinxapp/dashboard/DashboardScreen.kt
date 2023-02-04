package com.android.blinxapp.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.blinxapp.R
import com.android.blinxapp.common.CommonTitle

@Composable
fun DashboardScreen() {
    DashboardInitPage()
}


@Composable fun DashboardInitPage(){

    val scrollState = rememberScrollState()

    Column(modifier = Modifier
        .fillMaxSize().verticalScroll(scrollState)
        .background(colorScheme.primary)) {

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp, start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)){
            /**
             * A Box containing a constraint layout that
             * houses the notification and Profile
             */

            /**
             * Customers Greetings
             */
            CommonTitle( "Hello,","Oladotun")
            /**
             * Add funds to Wallet
             */
            WalltCard()

            /**
             * Add Your BVN
             */
            DashboardCard(painterResource(R.drawable.question_mark_icon),
                stringResource(R.string.add_bvn),
                stringResource(R.string.add_bvn_message))

            /**
             * Link Bank Account
             */
            DashboardCard(painterResource(R.drawable.bank),
                stringResource(R.string.link_bank),
                stringResource(R.string.link_bank_message))

            /**
             * Know Your Customer (KYC)
             */
            DashboardCard(painterResource(R.drawable.verified_user),
                stringResource(R.string.title_kyc),
                stringResource(R.string.message_kyc))
        }

    }
}

        @Preview(showBackground = true)
@Composable
fun DashboardScreenPreview(){
    DashboardScreen()
}