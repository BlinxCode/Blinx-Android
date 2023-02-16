package com.android.blinxapp.authenitcation.ui.signup.phone

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.blinxapp.R
import com.android.blinxapp.common.customviews.ContinueButtonButton
import com.android.blinxapp.common.customviews.PhoneInputField
import com.android.blinxapp.ui.theme.Typography
import com.android.blinxapp.ui.theme.secondaryGrey

@Composable
fun PhoneNumberScreen(
    onInputPhoneNumberButtonClicked: ()-> Unit,
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(end = 20.dp, start = 20.dp)) {

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .weight(1f, false)
        ) {
            //Title modifier
            Text(
                text = "What’s your",
                color = secondaryGrey,
                style = Typography.displayLarge,
                textAlign = TextAlign.Start
            )

            //Description modifier
            Text(
                text = "Phone Number?",
                style = Typography.displayLarge,
                textAlign = TextAlign.Start

            )
            PhoneForm(onInputPhoneNumberButtonClicked )
        }


    }

}

@Composable
fun PhoneForm(
    onInputPhoneNumberButtonClicked: () -> Unit
) {

    // Adding a Spacer of height 20dp
    Spacer(modifier = Modifier.height(25.dp))
    //Password
    Text(
        text = "Please share your personal phone number",
        style = Typography.labelMedium,
        textAlign = TextAlign.Start
    )

    // Adding a Spacer of height 20dp
    Spacer(modifier = Modifier.height(25.dp))
    //Password
    Text(
        text = "Phone number",
        style = Typography.labelMedium,
        textAlign = TextAlign.Start
    )

    Spacer(Modifier.size(16.dp))
    PhoneInputField()
    Spacer(Modifier.size(16.dp))
    ContinueButtonButton(onInputPhoneNumberButtonClicked, stringResource(R.string.continue_txt))

}

@Preview(showBackground = true)
@Composable
fun PhoneScreenScaffoldPreview(){
    PhoneNumberScreen(
        onInputPhoneNumberButtonClicked ={ }
    )
}