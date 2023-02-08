package com.android.blinxapp.dashboard.ui.presentation.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.android.blinxapp.R
import com.android.blinxapp.common.buttonColors
import com.android.blinxapp.common.cardColors
import com.android.blinxapp.common.CardTitleColors
import com.android.blinxapp.ui.theme.Typography
import com.android.blinxapp.ui.theme.containerColorBlack
import com.android.blinxapp.ui.theme.secondaryGrey
import com.android.blinxapp.ui.theme.whiteBlinx


@Composable
fun WalletCard() {
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
                .background(containerColorBlack),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)

                    .background(containerColorBlack)
            ) {

                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    val ( walletTitle, walletBalance,fundWallet) = createRefs()

                    Text(
                        text = stringResource(R.string.wallet_balance),
                        modifier = Modifier.constrainAs(walletTitle) {
                            start.linkTo(parent.start)
                        },
                        color = secondaryGrey,
                        style = Typography.titleSmall,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "₦0.00",
                        modifier = Modifier.constrainAs(walletBalance) {
                            top.linkTo(walletTitle.bottom)
                        },
                        color = Color.White,
                        style = Typography.bodyLarge,
                        textAlign = TextAlign.Start
                    )
                    Button(
                        modifier = Modifier.constrainAs(fundWallet){
                            end.linkTo(parent.end)
                        }
                            .width(130.dp)
                            .height(40.dp),
                        onClick = { },
                        colors = buttonColors(),
                        shape = RoundedCornerShape(20)
                    ) {


                        Text(
                            style = Typography.titleSmall,
                            text = stringResource(R.string.fund_wallet),
                            color = whiteBlinx,
                            modifier = Modifier
                        )
                    }

                }

                Spacer(modifier = Modifier.padding(5.dp))
            }
        }
    }
}