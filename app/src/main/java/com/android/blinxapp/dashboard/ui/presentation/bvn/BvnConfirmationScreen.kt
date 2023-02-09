package com.android.blinxapp.dashboard.ui.presentation.bvn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.blinxapp.R
import com.android.blinxapp.common.CommonTitle
import com.android.blinxapp.common.customviews.ContinueButtonButton
import com.android.blinxapp.common.customviews.PhoneInputField
import com.android.blinxapp.ui.theme.Typography

@Composable
fun BvnVerificationScreen(
    onProceedClicked: () -> Unit,

    ){
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary)) {

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .weight(1f, false)
        ) {
            //Title modifier
            CommonTitle("Verify your", "BVN")

            //Description modifier
            Spacer(Modifier.size(16.dp))
            Text(
                text = stringResource(R.string.bvn_label),
                style = Typography.labelSmall,
                textAlign = TextAlign.Start,
            )

            /**
             * Start Email  field
             */
            Spacer(Modifier.size(16.dp))
            Text(
                text = "Enter your BVN",
                style = Typography.labelMedium,
                textAlign = TextAlign.Start
            )

            Spacer(Modifier.size(16.dp))
            PhoneInputField()
            Spacer(Modifier.size(16.dp))
            /**
             * End of Email Field
             */


            /**
             * Verify BVN button
             */

            ContinueButtonButton(onProceedClicked,
                stringResource(R.string.continue_txt)
            )

            Spacer(Modifier.size(16.dp))
            Text(
                text = stringResource(R.string.why_bvn),
                style = Typography.labelSmall,
                textAlign = TextAlign.Start,
            )


        }
    }
}


@Preview(showBackground = true)
@Composable
fun BvnValidationLoginScreenScaffoldPreview(){
    BvnVerificationScreen(onProceedClicked = {})
}