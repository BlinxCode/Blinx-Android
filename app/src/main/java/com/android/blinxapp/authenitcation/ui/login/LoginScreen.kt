package com.android.blinxapp.authenitcation.ui.login

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
import com.android.blinxapp.R
import com.android.blinxapp.common.CommonTitle
import com.android.blinxapp.common.PasswordIconSetup
import com.android.blinxapp.common.customviews.ContinueButtonButton
import com.android.blinxapp.common.customviews.InputTextField
import com.android.blinxapp.common.outlineColors
import com.android.blinxapp.ui.theme.Typography


@Composable
fun LoginScreen(
    onProceedClicked: () -> Unit,
) {
    val passwordVisibility = remember { mutableStateOf(true) }
    val passwordErrorState = remember { mutableStateOf(false) }
    val password = remember { mutableStateOf(TextFieldValue()) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary)) {

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .weight(1f, false)
        ) {
            //Title modifier
            CommonTitle("Hello,", "Oladotun")

            //Description modifier
            Spacer(Modifier.size(16.dp))
            Text(
                text = "The simplest way to instantly fund wallet, automate payment, save, and invest.",
                style = Typography.labelSmall,
                textAlign = TextAlign.Start,
            )


            //Email
            Spacer(Modifier.size(16.dp))
            Text(
                text = "Email or Username",
                style = Typography.labelMedium,
                textAlign = TextAlign.Start
            )

            Spacer(Modifier.size(16.dp))
            InputTextField()

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
fun PasswordField(passwordVisibility: MutableState<Boolean>, password: MutableState<TextFieldValue>,
                  passwordErrorState: MutableState<Boolean>) {
    OutlinedTextField(

        modifier = Modifier.fillMaxWidth().height(50.dp),
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