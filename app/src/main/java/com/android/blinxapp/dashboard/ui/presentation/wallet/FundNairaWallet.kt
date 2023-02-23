package com.android.blinxapp.dashboard.ui.presentation.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.android.blinxapp.ui.theme.*
import kotlinx.coroutines.launch

import androidx.compose.material.icons.Icons
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FundNairaWallet(onProceedClicked: () ->Unit) {
    val scrollState = rememberScrollState()
    var openBottomSheet = rememberSaveable { mutableStateOf(false) }
    val skipHalfExpanded by remember { mutableStateOf(false) }
    val bottomSheetState = rememberSheetState(skipHalfExpanded = skipHalfExpanded)
    val hideBottomSheet = remember { mutableStateOf(false) }
    val buttonClicked = remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    var switchOn by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.background(MaterialTheme.colorScheme.primary).fillMaxSize()
    ){

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .safeContentPadding()
                .padding(start = 10.dp, end = 10.dp)){
            val (content, button) = createRefs()


            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .constrainAs(content){
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
                    ){
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
                        modifier = Modifier.constrainAs(switch){
                            start.linkTo(column.end)
                            end.linkTo(parent.end)
                        },
                        checked = switchOn,
                        onCheckedChange = { switchOn_ ->
                            switchOn = switchOn_
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = white,
                            checkedTrackColor = primaryGreen ,
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
                Card(
                    onClick  ={ buttonClicked.value = true },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ){
                    Box(
                        contentAlignment = Alignment.CenterStart,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(containerColorWhite)
                    ){

                        ConstraintLayout(
                            modifier = Modifier
                                .fillMaxWidth()
                                .safeContentPadding()
                                .padding(start = 10.dp, end = 10.dp)
                        ) {
                            val (text, arrow) = createRefs()

                            Text(
                                style = Typography.labelSmall,
                                text = stringResource(R.string.select_payment_method),
                                modifier = Modifier
                                    .padding(top = 5.dp)
                                    .constrainAs(text) {
                                        start.linkTo(parent.start)
                                    }
                            )
                            Icon(
                                imageVector = Icons.Filled.ExpandMore,
                                modifier = Modifier
                                    .padding(top = 5.dp)
                                    .constrainAs(arrow) {
                                        end.linkTo(parent.end)
                                    }
                                    .size(25.dp),
                                contentDescription = "drawable icons",
                            )
                        }
                    }
                }
            }

            //Continue button to show cards
            ContinueButtonButton(onProceedClicked ={ },
                stringResource(R.string.continue_txt),
                modifier = Modifier
                    .constrainAs(button){
                        bottom.linkTo(parent.bottom)
                    })
        }
    }

    // LaunchedEffect to handle  state cleanup
    LaunchedEffect(buttonClicked.value) {
        if (buttonClicked.value) {
            scope.launch {
                openBottomSheet.value= true}
            buttonClicked.value = false
        }
    }

    // BottomSheet for adding card or bank transfer.
    WalletBottomSheet(openBottomSheet = openBottomSheet,  scope, bottomSheetState, hideBottomSheet = hideBottomSheet)
    }


@Preview(showBackground = true)
@Composable
fun FundNairaWalletPreview() {
    FundNairaWallet(onProceedClicked = {})
}
