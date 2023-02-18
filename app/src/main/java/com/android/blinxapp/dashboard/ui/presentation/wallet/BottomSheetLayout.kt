package com.android.blinxapp.dashboard.ui.presentation.wallet

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.blinxapp.R
import com.android.blinxapp.common.cardTitleColors
import com.android.blinxapp.dashboard.ui.presentation.home.ComposeCard
import com.android.blinxapp.ui.theme.Typography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
/*
@OptIn(ExperimentalMaterial3Api::class)
*/

@Composable
fun BottomSheetLayout(coroutineScope: CoroutineScope, modalSheetState: ModalBottomSheetState) {

    val roundedCornerRadius = 12.dp

    BackHandler(modalSheetState.isVisible) {
        coroutineScope.launch { modalSheetState.hide() }
    }

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = roundedCornerRadius, topEnd = roundedCornerRadius),
        sheetContent = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {


                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(25.dp)
                ) {
                    androidx.compose.material3.Text(
                        "Select a payment method",
                        color = cardTitleColors(),
                        style = Typography.titleSmall,
                    )

                    /**
                     * Link Bank Account
                     */
                    ComposeCard(
                        onClicked = {
                            coroutineScope.launch { modalSheetState.hide() }
                        },
                        painterResource(R.drawable.debit_card),
                        stringResource(R.string.debit_card),
                        stringResource(R.string.debit_card_msg)
                    )

                    ComposeCard(
                        onClicked = {
                            coroutineScope.launch { modalSheetState.hide() }
                        },
                        painterResource(R.drawable.bank),
                        stringResource(R.string.bank_transfer),
                        stringResource(R.string.bank_transfer_msg)
                    )

                }

            }
        }
    ) {

    }
}