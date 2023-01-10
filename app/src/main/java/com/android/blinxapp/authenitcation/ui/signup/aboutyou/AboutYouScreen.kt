package com.android.blinxapp.authenitcation.ui.signup.aboutyou

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
import com.android.blinxapp.R
import com.android.blinxapp.common.customviews.ContinueButtonButton
import com.android.blinxapp.ui.theme.Typography
import com.android.blinxapp.ui.theme.primaryGreen
import com.android.blinxapp.ui.theme.secondaryGrey


@Composable
fun AboutYouScreen(
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
                text = "Tell us more",
                color = secondaryGrey,
                style = Typography.displayLarge,
                textAlign = TextAlign.Start
            )

            //Description modifier
            Text(
                text = "About yourself",
                style = Typography.displayLarge,
                textAlign = TextAlign.Start

            )
            //Description modifier
            Spacer(Modifier.size(16.dp))
            Text(
                text = "You are almost done. we would like to know your name",
                style = Typography.titleSmall,
                color = primaryGreen,
                textAlign = TextAlign.Start

            )
            AboutYouForm()
            //Getting started button
            ContinueButtonButton(onProceedClicked,
                stringResource(R.string.continue_txt))

        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutYouScaffoldPreview(){
    AboutYouScreen(onProceedClicked ={})
}