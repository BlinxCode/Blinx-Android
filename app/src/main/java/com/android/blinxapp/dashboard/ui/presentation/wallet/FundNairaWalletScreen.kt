package com.android.blinxapp.dashboard.ui.presentation.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.android.blinxapp.R
import com.android.blinxapp.common.CommonTitle
import com.android.blinxapp.common.customviews.ContinueButtonButton
import com.android.blinxapp.common.customviews.PhoneInputField
import com.android.blinxapp.dashboard.ui.presentation.home.CustomCardWithText
import com.android.blinxapp.ui.theme.*
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FundNairaWallet(
    onProceedClicked: () -> Unit, onDebitCardClicked: () -> Unit,
    onBankTransferClicked: () -> Unit, modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    val openFundingTypeBottomSheet = rememberSaveable { mutableStateOf(false) }
    val hideBottomSheet = remember { mutableStateOf(false) }
    val buttonClicked = remember { mutableStateOf(false) }
    val bottomSheetState = rememberSheetState(false)

    val scope = rememberCoroutineScope()

    var switchOn by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize()
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .safeContentPadding()
        ) {
            val (content, button) = createRefs()


            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .constrainAs(content) {
                        top.linkTo(parent.top)
                    }
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                //Title modifier
                CommonTitle("Fund your", "Naira Wallet")

                /**
                 * Start Email  field
                 */

                /**
                 * Start Email  field
                 */

                /**
                 * Start Email  field
                 */

                /**
                 * Start Email  field
                 */
                Spacer(Modifier.size(16.dp))
                Text(
                    text = "Amount",
                    style = Typography.labelMedium,
                    textAlign = TextAlign.Start
                )

                Spacer(Modifier.size(16.dp))
                PhoneInputField()
                Spacer(Modifier.size(16.dp))
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .safeContentPadding()
                        .padding()
                ) {
                    val (column, switch) = createRefs()


                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .constrainAs(column) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                end.linkTo(switch.start, margin = 62.dp)
                                width = Dimension.fillToConstraints
                            }
                    ) {
                        Text(
                            text = "Smart deposits",
                            style = Typography.titleSmall,
                            textAlign = TextAlign.Start
                        )

                        Text(
                            text = stringResource(R.string.smart_deposit_text),
                            style = Typography.labelSmall,
                            textAlign = TextAlign.Start
                        )

                    }

                    Switch(
                        modifier = Modifier.constrainAs(switch) {
                            start.linkTo(column.end)
                            end.linkTo(parent.end)
                        },
                        checked = switchOn,
                        onCheckedChange = { switchOn_ ->
                            switchOn = switchOn_
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = white,
                            checkedTrackColor = primaryGreen,
                            uncheckedTrackColor = grey,
                            uncheckedThumbColor = white,
                            uncheckedBorderColor = grey,
                        )
                    )
                }
                /**
                 * End of Email Field
                 */
                /**
                 * Select Payment method
                 */

                Spacer(Modifier.size(16.dp))

                CustomCardWithText(buttonClicked,
                    stringResource(R.string.select_payment_method),
                    true)
            }

            //Continue button to show cards
            ContinueButtonButton(onProceedClicked = {},
                stringResource(R.string.continue_txt),
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .constrainAs(button) {
                        bottom.linkTo(parent.bottom)
                    })
        }
    }


// DebitCardInfoView(openBottomSheet = openDebitCardBottomSheet,  scope, modalBottomSheetState, hideBottomSheet = hideBottomSheet)
    SelectFundingType(
        openBottomSheet = openFundingTypeBottomSheet,
        scope,
        bottomSheetState,
        hideBottomSheet = hideBottomSheet,
        onDebitCardClicked,
        onBankTransferClicked
    )
    // LaunchedEffect to handle  state cleanup
    LaunchedEffect(buttonClicked.value
        ) {
        if (buttonClicked.value) {
            scope.launch { openFundingTypeBottomSheet.value = true }
            buttonClicked.value = false
        }
    }

}

@Preview(showBackground = true)
@Composable
fun FundNairaWalletPreview() {
    FundNairaWallet(onProceedClicked = {}, modifier = Modifier,
        onDebitCardClicked = {}, onBankTransferClicked = {})
}
