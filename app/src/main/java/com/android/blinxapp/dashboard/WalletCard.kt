package com.android.blinxapp.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.android.blinxapp.R
import com.android.blinxapp.common.buttonColors
import com.android.blinxapp.common.cardColors
import com.android.blinxapp.common.CardTitleColors
import com.android.blinxapp.ui.theme.Typography
import com.android.blinxapp.ui.theme.secondaryGrey
import com.android.blinxapp.ui.theme.whiteBlinx


@Composable
fun WalltCard() {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(15.dp),

        modifier = Modifier
            .fillMaxWidth()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .safeContentPadding()
                .background(cardColors()),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)

                    .background(cardColors())
            ) {
                Text(
                    text = stringResource(R.string.wallet_balance),
                    modifier = Modifier,
                    color = secondaryGrey,
                    style = Typography.titleSmall,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = "₦0.00",
                    modifier = Modifier,
                    color = CardTitleColors(),
                    style = Typography.bodyLarge,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = stringResource(R.string.fund_wallet_message),
                    modifier = Modifier,
                    color = secondaryGrey,
                    style = Typography.labelSmall,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.padding(5.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    onClick = { },
                    colors = buttonColors(),
                    shape = RoundedCornerShape(20)
                ) {

                    Icon(
                        painter = painterResource(R.drawable.ic_add),
                        modifier = Modifier
                            .size(30.dp)
                            .weight(1f)
                            .offset(x = (-70).dp)
                            .align(Alignment.CenterVertically),
                        contentDescription = "drawable icons",
                    )

                    Text(
                        style = Typography.titleSmall,
                        text = stringResource(R.string.fund_wallet),
                        color = whiteBlinx,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .weight(1f)
                            .padding(bottom = 4.dp)
                            .offset(x = (-140).dp) //default icon width = 24.dp
                    )
                }
            }
        }
    }
}