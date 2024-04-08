package com.android.blinxapp.feature.presentation.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.blinxapp.R
import com.android.blinxapp.feature.presentation.components.common.CommonTitle
import com.android.blinxapp.feature.presentation.home.CardComingSoon
import com.android.blinxapp.feature.presentation.home.ComposeCard
import com.android.blinxapp.ui.theme.Typography


@Composable
fun FundWalletScreen(
    onNairaClicked: () -> Unit,
    onDollarClicked: () -> Unit,
    modifier: Modifier = Modifier,

) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .verticalScroll(scrollState)
                .weight(1f, false)
        ) {
            //Title modifier
            CommonTitle("Fund your", "Wallet")

            //Description modifier
            Spacer(Modifier.size(16.dp))
            Text(
                text = stringResource(R.string.fund_wallet_note),
                style = Typography.labelSmall,
                textAlign = TextAlign.Start
            )

            /**
             * Start Email  field
             */

            //
            ComposeCard(
                onNairaClicked,
                painterResource(R.drawable.naira),
                stringResource(R.string.naira_wallet),
                stringResource(R.string.naira_wallet_txt)
            )

            CardComingSoon(
                onDollarClicked,
                painterResource(R.drawable.dollar),
                stringResource(R.string.dollar_card),
                stringResource(R.string.wallet_text)
            )
            Spacer(Modifier.size(16.dp))

        }

    }
}


@Preview(showBackground = true)
@Composable
fun BvnConfirmationScaffoldPreview() {
    FundWalletScreen(onNairaClicked = {}, onDollarClicked = {}, modifier = Modifier)
}