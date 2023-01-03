package com.example.blinxapp.authenitcation.ui.sucess

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blinxapp.R
import com.example.blinxapp.common.customviews.ContinueButtonButton
import com.example.blinxapp.ui.theme.Typography
import com.example.blinxapp.ui.theme.primaryGreen
import com.example.blinxapp.ui.theme.secondaryGrey


@Composable
fun SuccessScreen(
    onProceedClicked: () -> Unit,
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary)
            .padding(end = 20.dp, start = 20.dp)) {

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .weight(1f, false)
        ) {
            //Title modifier
            Text(
                text = "Thank you for choosing",
                color = secondaryGrey,
                style = Typography.displayLarge,
                textAlign = TextAlign.Start
            )

            //Description modifier
            Text(
                text = "Blinx, Olajide",
                style = Typography.displayLarge,
                textAlign = TextAlign.Start

            )
            //Description modifier
            Spacer(Modifier.size(16.dp))
            Text(
                text = "You have successfully created a blinx profile",
                style = Typography.titleSmall,
                textAlign = TextAlign.Start,
                color = primaryGreen

                )
            //Getting started button
            ContinueButtonButton(onProceedClicked, stringResource(R.string.proceed_login))

        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutYouScaffoldPreview(){
    SuccessScreen(onProceedClicked ={})
}