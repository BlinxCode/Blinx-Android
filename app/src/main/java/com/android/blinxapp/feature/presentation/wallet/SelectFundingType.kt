package com.android.blinxapp.feature.presentation.wallet

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.blinxapp.R
import com.android.blinxapp.feature.presentation.components.common.cardTitleColors
import com.android.blinxapp.feature.presentation.components.common.modalsheetColor
import com.android.blinxapp.feature.presentation.home.ComposeCard
import com.android.blinxapp.ui.theme.Typography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun SelectFundingType(
    openBottomSheet: MutableState<Boolean>,
    scope: CoroutineScope,
    bottomSheetState: SheetState,
    hideBottomSheet: MutableState<Boolean>,
    onDebitCardClicked: () -> Unit,
    onBankTransferClicked: () -> Unit

    ) {

    BackHandler(bottomSheetState.isVisible) {
        scope.launch { bottomSheetState.hide() }
    }

    var openBottomSheet1 = openBottomSheet.value
    if (openBottomSheet1) {
        ModalBottomSheet(
            containerColor = modalsheetColor(),
            onDismissRequest = {
                openBottomSheet1 = false
                hideBottomSheet.value = true },
            sheetState = bottomSheetState,

            content ={
                // Note: If you provide logic outside of onDismissRequest to remove the sheet,
                // you must additionally handle intended state cleanup, if any.
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(25.dp)
                ) {
                    Text("Select a payment method",
                        color = cardTitleColors(),
                        style = Typography.titleSmall,
                    )

                    /**
                     * Fund wallet with debit card
                     */
                    ComposeCard(
                        onClicked = {
                            hideBottomSheet.value = true
                            onDebitCardClicked()
                        },
                        painterResource(R.drawable.debit_card),
                        stringResource(R.string.debit_card),
                        stringResource(R.string.debit_card_msg)
                    )
                    /**
                     * Link bank account
                     */
                    ComposeCard(
                        onClicked = {
                            hideBottomSheet.value = true
                            onBankTransferClicked()
                        },
                        painterResource(R.drawable.bank),
                        stringResource(R.string.bank_transfer),
                        stringResource(R.string.bank_transfer_msg)
                    )
                }
            }
        )
    }

    // LaunchedEffect to handle  state cleanup
    LaunchedEffect(hideBottomSheet.value){
        if (hideBottomSheet.value){
                scope.launch {
                    bottomSheetState.hide()
                }.invokeOnCompletion {
                    if (!bottomSheetState.isVisible) {
                        openBottomSheet1 = false
                        openBottomSheet.value = false
                        hideBottomSheet.value = false
                    }
                }
        }
    }

}
