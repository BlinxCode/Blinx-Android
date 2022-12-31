package com.example.blinxapp.signup.ui.phone

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blinxapp.R
import com.example.blinxapp.common.outlineColors
import com.example.blinxapp.signup.ui.form.*
import com.example.blinxapp.ui.theme.Typography
import com.example.blinxapp.ui.theme.primaryGreen
import com.example.blinxapp.ui.theme.secondaryGrey
import com.example.blinxapp.ui.theme.whiteBlinx

@Composable
fun ConfirmPhoneScreen(
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
    SendPhoneCodeButton(onInputPhoneNumberButtonClicked)

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneInputField() {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = outlineColors(),
        value = text,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        onValueChange = { text = it }
    )
}

@Composable
fun SendPhoneCodeButton(onInputPhoneNumberButtonClicked: () -> Unit) {
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
            onClick = { onInputPhoneNumberButtonClicked()},
            colors = buttonColors,
            shape = RoundedCornerShape(20)
        ) {
            Text(
                text = stringResource(R.string.continue_txt),
                style = Typography.labelSmall,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ConfirmPhoneScreenScaffoldPreview(){
    ConfirmPhoneScreen(
        onInputPhoneNumberButtonClicked ={ }
    )
}