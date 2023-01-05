package com.example.blinxapp.authenitcation.ui.signup.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.example.blinxapp.ui.theme.whiteBlinx



@Composable
 fun SignupFormScreen(
    onSignupButtonClicked: () -> Unit,
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
                text = "Create",
                color = secondaryGrey,
                style = Typography.displayLarge,
                textAlign = TextAlign.Start
            )

            //Description modifier
            Text(
                text = "your account",
                style = Typography.displayLarge,
                textAlign = TextAlign.Start

            )
            SignupForm()
            //Getting started button

            ContinueButtonButton(onSignupButtonClicked, stringResource(R.string.register))

        }


    }

}
@Preview(showBackground = true)
@Composable
fun SignupScaffoldPreview(){
    SignupFormScreen(onSignupButtonClicked ={})
}