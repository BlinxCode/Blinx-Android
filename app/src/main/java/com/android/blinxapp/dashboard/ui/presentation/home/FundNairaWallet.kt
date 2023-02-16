package com.android.blinxapp.dashboard.ui.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

@Composable
fun FundNairaWallet(onProceedClicked: () -> Unit) {
    val scrollState = rememberScrollState()

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


            ContinueButtonButton(onProceedClicked,
                stringResource(R.string.continue_txt),
                modifier = Modifier
                    .constrainAs(button){
                    bottom.linkTo(parent.bottom)
                })

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
                            checkedThumbColor = if(switchOn) electricBlue else grey,
                            checkedTrackColor = if(switchOn) primaryGreen else secondaryGrey
                        )
                    )
                }
                /**
                 * End of Email Field
                 */
                /**
                 * End of Email Field
                 */

                Spacer(Modifier.size(16.dp))
                Card(
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
                                color = secondaryGrey,
                                modifier = Modifier
                                    .padding(top = 5.dp)
                                    .constrainAs(text) {
                                        start.linkTo(parent.start)
                                    }
                            )
                            Icon(
                                imageVector = Icons.Filled.ExpandMore,
                                modifier = Modifier
                                    .constrainAs(arrow) {
                                        end.linkTo(parent.end)
                                    }
                                    .size(30.dp),
                                contentDescription = "drawable icons",
                            )
                        }


                    }
                }
            }
        }

    }

    }


@Preview(showBackground = true)
@Composable
fun FundNairaWalletPreview() {
    FundNairaWallet(onProceedClicked = {})
}
