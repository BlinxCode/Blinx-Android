package com.example.blinxapp.signup.ui.form

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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blinxapp.R
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
            Spacer(Modifier.size(16.dp))
            RegisterButtonButton(onSignupButtonClicked)

        }


    }

}

@Composable
private fun RegisterButtonButton(onSignupButtonClicked: () -> Unit) {
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = primaryGreen,
        contentColor = contentColorFor(backgroundColor = whiteBlinx)
    )
    Column(modifier = Modifier
        .padding(bottom = 40.dp)
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            onClick = { onSignupButtonClicked()},
            colors = buttonColors,
            shape = RoundedCornerShape(20)
        ) {
            Text(
                text = stringResource(R.string.register),
                style = Typography.labelSmall,
            )
        }
        //Login if already have an account
        LoginText()

    }
}

@Composable
fun LoginText() {
    val annotatedString = buildAnnotatedString {
        append("By registering you agree to our")
        withStyle(style = SpanStyle(color = primaryGreen,
            fontWeight = FontWeight.Bold)
        ) {
            append(" Terms & Conditions")
        }
        append(" and")
        withStyle(style = SpanStyle(color = primaryGreen,
            fontWeight = FontWeight.Bold)
        ) {
            append(" privacy policy")
        }
    }

    Text(text = annotatedString,
        modifier = Modifier
            .padding( top = 50.dp, bottom = 50.dp),
        style = Typography.labelSmall,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun SignupScaffoldPreview(){
    SignupFormScreen(onSignupButtonClicked ={})
}