package com.android.blinxapp.ui.feature.presentation.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.blinxapp.R
import com.android.blinxapp.ui.feature.presentation.components.common.CommonTitle
import com.android.blinxapp.ui.feature.presentation.components.common.cardTitleColors
import com.android.blinxapp.ui.feature.presentation.components.common.customviews.ContinueButtonButton
import com.android.blinxapp.ui.feature.presentation.components.common.customviews.PhoneInputField
import com.android.blinxapp.ui.theme.Typography

@Composable

fun DebitCardInfoView(addDebitCard: () -> Unit) {

    Column(
        modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(25.dp)

    ) {
        CommonTitle("Attach your naira", "debit card")

        Text(
            "Card number",
            color = cardTitleColors(),
            style = Typography.labelSmall,
        )
        PhoneInputField(placeholder = "16 digits on your credit card")


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Expiry date",
                color = cardTitleColors(),
                style = Typography.labelSmall
            )
            Text("CVC number",
                color = cardTitleColors(),
                style = Typography.labelSmall
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            PhoneInputField(modifier =Modifier.weight(1f), placeholder = "MM/YY")
            PhoneInputField(modifier =Modifier.weight(1f), placeholder = "CVC")
        }

        Text(
            "Payment is secured by Paystack",
            color = cardTitleColors(),
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            style = Typography.labelSmall,
        )
        //Continue button to show cards
        ContinueButtonButton(onProceedClicked ={ addDebitCard()},
            stringResource(R.string.add_card),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DebitCardPreview() {
    DebitCardInfoView(addDebitCard = {})
}