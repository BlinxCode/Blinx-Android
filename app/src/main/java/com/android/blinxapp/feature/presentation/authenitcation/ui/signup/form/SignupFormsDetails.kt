package com.android.blinxapp.feature.presentation.authenitcation.ui.signup.form

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.android.blinxapp.feature.presentation.components.common.PasswordIconSetup
import com.android.blinxapp.feature.presentation.components.common.outlineColors
import com.android.blinxapp.ui.theme.Typography


@Composable
fun SignupForm() {
    val context = LocalContext.current

    val email = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }
    val passwordVisibility = remember { mutableStateOf(true) }
    val passwordErrorState = remember { mutableStateOf(false) }
    val confirmPassword = remember { mutableStateOf(TextFieldValue()) }

    val emailErrorState = remember { mutableStateOf(false) }
    val confirmPasswordErrorState = remember { mutableStateOf(false) }

    // Adding a Spacer of height 20dp
    Spacer(modifier = Modifier.height(25.dp))
    //Email field
    Text(
        text = "Email",
        style = Typography.labelMedium,
        textAlign = TextAlign.Start
    )
    Spacer(Modifier.size(16.dp))
    EmailInputField()
    
    //Password
    Text(
        modifier = Modifier.padding(top = 25.dp),
        text = "Password",
        style = Typography.labelMedium,
        textAlign = TextAlign.Start
    )
    Spacer(Modifier.size(16.dp))
    PasswordField(passwordVisibility, password, passwordErrorState)

    //Confirm Password
    Text(
        modifier = Modifier.padding(top = 25.dp),
        text = "Confirm Password",
        style = Typography.labelMedium,
        textAlign = TextAlign.Start
    )
    Spacer(Modifier.size(16.dp))
    ConfirmPasswordField(passwordVisibility, confirmPassword, confirmPasswordErrorState)

    //Referal Id
    Text(
        modifier = Modifier.padding(top = 25.dp),
        text = "Referral ID",
        style = Typography.labelMedium,
        textAlign = TextAlign.Start
    )
    Spacer(Modifier.size(16.dp))
    ReferralIDField()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailInputField() {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(

        textStyle = Typography.bodyMedium,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth().height(50.dp),
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
        textStyle = Typography.bodyMedium,
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmPasswordField(passwordVisibility: MutableState<Boolean>, password:
MutableState<TextFieldValue>, passwordErrorState: MutableState<Boolean>) {
    OutlinedTextField(
        textStyle = Typography.bodyMedium,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth().height(50.dp),
        trailingIcon = {
            PasswordIconSetup(passwordVisibility)
        },
        visualTransformation = if (passwordVisibility.value) PasswordVisualTransformation()
        else VisualTransformation.None,
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



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReferralIDField() {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        textStyle = Typography.bodyMedium,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth().height(50.dp),
        colors = outlineColors(),
        value = text,
        onValueChange = { text = it }
    )
}
