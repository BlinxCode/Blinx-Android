package com.example.blinxapp.authenitcation.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blinxapp.R
import com.example.blinxapp.common.PasswordIconSetup
import com.example.blinxapp.common.customviews.ContinueButtonButton
import com.example.blinxapp.common.outlineColors
import com.example.blinxapp.ui.theme.Typography
import com.example.blinxapp.ui.theme.primaryGreen
import com.example.blinxapp.ui.theme.secondaryGrey


@Composable
fun LoginScreen(
    onProceedClicked: () -> Unit,
) {
    val passwordVisibility = remember { mutableStateOf(true) }
    val passwordErrorState = remember { mutableStateOf(false) }
    val password = remember { mutableStateOf(TextFieldValue()) }

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
                text = "Welcome back,",
                color = secondaryGrey,
                style = Typography.displayLarge,
                textAlign = TextAlign.Start
            )

            //Description modifier
            Text(
                text = "Oladotun",
                style = Typography.displayLarge,
                textAlign = TextAlign.Start

            )
            //Description modifier
            Spacer(Modifier.size(16.dp))
            Text(
                text = "The simplest way to instantly fund wallet, automate payment, save, and invest.",
                style = Typography.titleSmall,
                textAlign = TextAlign.Start,
                color = primaryGreen

            )


            //Email
            Spacer(Modifier.size(16.dp))
            Text(
                text = "Email or Username",
                style = Typography.labelMedium,
                textAlign = TextAlign.Start
            )

            Spacer(Modifier.size(16.dp))
            EmailOrUsername()

            //Password
            Spacer(Modifier.size(16.dp))
            Text(
                text = "Password",
                style = Typography.labelMedium,
                textAlign = TextAlign.Start
            )
            Spacer(Modifier.size(16.dp))
            PasswordField(
                passwordVisibility,
                password,
                passwordErrorState
            )
            Spacer(Modifier.size(16.dp))

            //Login  button
            ContinueButtonButton(onProceedClicked,
                stringResource(R.string.login))

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailOrUsername() {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = outlineColors(),
        value = text,
        onValueChange = { text = it }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(passwordVisibility: MutableState<Boolean>, password: MutableState<TextFieldValue>,
                  passwordErrorState: MutableState<Boolean>) {
    OutlinedTextField(

        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        trailingIcon = {
            PasswordIconSetup(passwordVisibility)
        },
        visualTransformation = if (passwordVisibility.value) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = outlineColors(),
        value = password.value,
        onValueChange = {
            if (passwordErrorState.value) {
                passwordErrorState.value = false
            }
            password.value = it
        }
    )
}



@Preview(showBackground = true)
@Composable
fun LoginScreenScaffoldPreview(){
    LoginScreen(onProceedClicked ={})
}