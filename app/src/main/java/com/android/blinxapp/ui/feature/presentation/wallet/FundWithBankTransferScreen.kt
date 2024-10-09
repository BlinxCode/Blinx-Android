package com.android.blinxapp.ui.feature.presentation.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.android.blinxapp.R
import com.android.blinxapp.ui.feature.presentation.components.common.CommonTitle
import com.android.blinxapp.ui.feature.presentation.components.common.customviews.ContinueButtonButton
import com.android.blinxapp.ui.feature.presentation.home.CustomCardWithText
import com.android.blinxapp.ui.theme.Typography


@Composable
fun FundWithBankTransferScreen(
    onProceedClicked: () -> Unit,
    modifier: Modifier = Modifier,

    ){
    val scrollState = rememberScrollState()
    val buttonClicked = remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary)) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .safeContentPadding()
                .padding()
        ) {
            val (column, button) = createRefs()
            Column(
                modifier = Modifier.constrainAs(column){start.linkTo(parent.start)
                top.linkTo(parent.top)}
                    .verticalScroll(scrollState)

            ) {

                /**
                 * Title
                 */
                CommonTitle("Bank", "Transfer")

                /**
                 * Subtitle note
                 */
                Spacer(Modifier.size(16.dp))
                Text(
                    text = stringResource(R.string.transfer_to_wallet),
                    style = Typography.labelSmall,
                    textAlign = TextAlign.Start,
                )
                Spacer(Modifier.size(16.dp))

                /**
                 * Bank Name
                 */

                Text(
                    text = stringResource(R.string.bank_name_txt),
                    style = Typography.labelSmall,
                    textAlign = TextAlign.Start,
                )
                Spacer(Modifier.size(16.dp))
                CustomCardWithText(buttonClicked,
                    stringResource(R.string.bank_name),
                    false)
                Spacer(Modifier.size(16.dp))

                /**
                 * Account Number
                 */
                Text(
                    text = stringResource(R.string.account_number_txt),
                    style = Typography.labelSmall,
                    textAlign = TextAlign.Start,
                )
                Spacer(Modifier.size(16.dp))
                CustomCardWithText(buttonClicked,
                    stringResource(R.string.account_number),
                    false, isCopy = true)
                Spacer(Modifier.size(16.dp))

                /**
                 * Account Name
                 */
                Text(
                    text = stringResource(R.string.account_name_txt),
                    style = Typography.labelSmall,
                    textAlign = TextAlign.Start,
                )
                Spacer(Modifier.size(16.dp))
                CustomCardWithText(buttonClicked,
                    stringResource(R.string.account_name),
                    false)
                Spacer(Modifier.size(16.dp))
            }

            ContinueButtonButton(onProceedClicked,
                stringResource(R.string.i_sent_the_money),
                modifier = Modifier.padding(bottom = 30.dp).constrainAs(button){
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun FundWithBankTransferPreview(){
    FundWithBankTransferScreen(onProceedClicked = {}, modifier = Modifier)
}