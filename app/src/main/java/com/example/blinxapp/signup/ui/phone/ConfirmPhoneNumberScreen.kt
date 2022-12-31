package com.example.blinxapp.signup.ui.phone

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blinxapp.R
import com.example.blinxapp.signup.ui.form.*
import com.example.blinxapp.ui.theme.Typography
import com.example.blinxapp.ui.theme.primaryGreen
import com.example.blinxapp.ui.theme.secondaryGrey
import com.example.blinxapp.ui.theme.whiteBlinx

@Composable
fun ConfirmPhoneNumberScreen(
    onPhoneConfirmButtonClicked: ()-> Unit,
    onPhoneConfirmResendButtonClicked: ()  -> Unit
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
                text = "Confirm your",
                color = secondaryGrey,
                style = Typography.displayLarge,
                textAlign = TextAlign.Start
            )

            //Description modifier
            Text(
                text = "Phone Number",
                style = Typography.displayLarge,
                textAlign = TextAlign.Start

            )
            PhoneForm(onPhoneConfirmButtonClicked,onPhoneConfirmResendButtonClicked )
        }


    }

}

@Composable
fun PhoneForm(
    onEmailConfirmButtonClicked: () -> Unit,
    onEmailConfirmResendButtonClicked: () -> Unit
) {

    val password = remember { mutableStateOf(TextFieldValue()) }
    val passwordVisibility = remember { mutableStateOf(true) }
    val passwordErrorState = remember { mutableStateOf(false) }

    // Adding a Spacer of height 20dp
    Spacer(modifier = Modifier.height(25.dp))

    //Email
    Text(
        text = "+234 816 354 4839",
        style = Typography.labelMedium,
        textAlign = TextAlign.Start,
        color = primaryGreen
    )

    // Adding a Spacer of height 20dp
    Spacer(modifier = Modifier.height(25.dp))
    //Password
    Text(
        text = "We have sent a code to your phone number to confirm that it’s actually yours.",
        style = Typography.labelMedium,
        textAlign = TextAlign.Start
    )

    // Adding a Spacer of height 20dp
    Spacer(modifier = Modifier.height(25.dp))
    //Password
    Text(
        text = "Verification code",
        style = Typography.labelMedium,
        textAlign = TextAlign.Start
    )

    Spacer(Modifier.size(16.dp))
    PasswordField(passwordVisibility, password, passwordErrorState)
    Spacer(Modifier.size(16.dp))
    ConfirmPhoneButton(onEmailConfirmButtonClicked, onEmailConfirmResendButtonClicked)

}

@Composable
fun ConfirmPhoneButton(onEmailConfirmButtonClicked: () -> Unit, onEmailConfirmResendButtonClicked: () ->Unit ) {
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = primaryGreen,
        contentColor = contentColorFor(backgroundColor = whiteBlinx)
    )

    Column(modifier = Modifier
        .padding(bottom = 40.dp, top = 27.dp)
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            onClick = { onEmailConfirmButtonClicked()},
            colors = buttonColors,
            shape = RoundedCornerShape(20)
        ) {
            Text(
                text = stringResource(R.string.confirmCode),
                style = Typography.labelSmall,
            )
        }
        // Adding a Spacer of height 20dp
        Spacer(modifier = Modifier.height(25.dp))
        //Password
        Text(
            modifier = Modifier.clickable(enabled = true) {
                onEmailConfirmResendButtonClicked()
            },
            text = "Resend code",
            style = Typography.labelMedium,
            textAlign = TextAlign.Start,
            textDecoration = TextDecoration.Underline,
            fontWeight = FontWeight.Bold
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ConfirmPhoneScaffoldPreview(){
    ConfirmPhoneNumberScreen(
        onPhoneConfirmButtonClicked ={ },
        onPhoneConfirmResendButtonClicked={}
    )
}