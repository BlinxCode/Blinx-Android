package com.android.blinxapp.dashboard.ui.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.blinxapp.R
import com.android.blinxapp.common.CommonTitle
import com.android.blinxapp.dashboard.ui.presentation.wallet.WalletCard

@Composable
fun HomeScreen() {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(MaterialTheme.colorScheme.primary)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp, start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            /**
             * A Box containing a constraint layout that
             * houses the notification and Profile
             */
            /**
             * A Box containing a constraint layout that
             * houses the notification and Profile
             */


            /**
             * Customers Greetings
             */
            /**
             * A Box containing a constraint layout that
             * houses the notification and Profile
             */
            /**
             * Customers Greetings
             */
            /**
             * A Box containing a constraint layout that
             * houses the notification and Profile
             */

            /**
             * Customers Greetings
             */



            /**
             * Customers Greetings
             */
            CommonTitle("Hello,", "Oladotun")
            /**
             * Add funds to Wallet
             */
            /**
             * Add funds to Wallet
             */
            /**
             * Add funds to Wallet
             */
            /**
             * Add funds to Wallet
             */
            WalletCard()

            /**
             * Add Your BVN
             */

            /**
             * Add Your BVN
             */

            /**
             * Add Your BVN
             */

            /**
             * Add Your BVN
             */
            DashboardCard(
                painterResource(R.drawable.question_mark_icon),
                stringResource(R.string.add_bvn),
                stringResource(R.string.add_bvn_message)
            )

            /**
             * Link Bank Account
             */

            /**
             * Link Bank Account
             */

            /**
             * Link Bank Account
             */

            /**
             * Link Bank Account
             */
            DashboardCard(
                painterResource(R.drawable.bank),
                stringResource(R.string.link_bank),
                stringResource(R.string.link_bank_message)
            )

            /**
             * Know Your Customer (KYC)
             */

            /**
             * Know Your Customer (KYC)
             */

            /**
             * Know Your Customer (KYC)
             */

            /**
             * Know Your Customer (KYC)
             */
            DashboardCard(
                painterResource(R.drawable.verified_user),
                stringResource(R.string.title_kyc),
                stringResource(R.string.message_kyc)
            )
        }

    }
}
