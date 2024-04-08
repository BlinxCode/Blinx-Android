package com.android.blinxapp.feature.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.android.blinxapp.R
import com.android.blinxapp.feature.presentation.components.common.ButtonGreenColor
import com.android.blinxapp.ui.theme.Typography
import com.android.blinxapp.ui.theme.containerColorBlack
import com.android.blinxapp.ui.theme.secondaryGrey
import com.android.blinxapp.ui.theme.white

@Composable
fun WalletCard(walletClick: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
        .fillMaxWidth()
    ) {
        Box(modifier = Modifier.background(containerColorBlack)){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .safeContentPadding()
                    .padding(20.dp)
                    .background(containerColorBlack),
                verticalArrangement = Arrangement.spacedBy(10.dp)
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
                        modifier = Modifier
                            .constrainAs(fundWallet) {
                                end.linkTo(parent.end)
                            }
                            .width(130.dp)
                            .height(40.dp),
                        onClick = { walletClick()},
                        colors = ButtonGreenColor(),
                        shape = RoundedCornerShape(20)
                    ) {


                        Text(
                            style = Typography.titleSmall,
                            text = stringResource(R.string.fund_wallet),
                            color = white,
                            modifier = Modifier
                        )
                    }

                }

                Spacer(modifier = Modifier.padding(5.dp))
            }

        }

    }
}


@Preview(showBackground = true)
@Composable
fun WalletCardPreview() {
    WalletCard(walletClick = {})
}