// Package for handling the PIN setup screen for authentication
package com.android.blinxapp.ui.feature.presentation.authenitcation.ui.pin

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.blinxapp.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.blinxapp.core.Constants.WELCOME_BACK_MESSAGE
import com.android.blinxapp.core.Constants.WRONG_PIN_MESSAGE
import com.android.blinxapp.core.RequestState
import com.android.blinxapp.domain.model.UserDTO
import com.android.blinxapp.ui.feature.presentation.components.ProgressBar
import com.android.blinxapp.ui.feature.viewmodel.ProfileViewModel
import com.android.blinxapp.ui.theme.Typography
import com.olajide.pinviewscreen.presentation.ComposablePinView


@Composable
fun PinSetupScreen(profileViewModel: ProfileViewModel = hiltViewModel(),
                   onProceedClicked: () -> Unit) {

    var isLoading by remember { mutableStateOf(true) }
    val pin = remember { mutableStateOf("") }
    val count = remember { mutableIntStateOf(1) }
    val context = LocalContext.current
    val charLimit = 4
    val enteredPins =  mutableListOf<String>() // Renamed for clarity


    LaunchedEffect(Unit) {
        profileViewModel.getUserProfileInDB()
        enteredPins.clear()
    }

    Box(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        ProgressBar(isLoading)

        when (val userProfile = profileViewModel.getUserProfileResponse) {
            is RequestState.Loading -> {
                Log.d("userProfile1", "is loading")
                isLoading = true
            }
            is RequestState.Success -> userProfile.data?.let { user ->
                profileViewModel.userProfile = user
                isLoading = false

                if (!user.displayName.isNullOrEmpty()) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Log.d("userProfile1", "userProfile: ${user.displayName}")

                        CheckPinOrSetup(
                            charLimit, enteredPins, context, onProceedClicked, count, pin, profileViewModel, user
                        )

                        ComposablePinView(
                            charLimit = charLimit,
                            text = "",
                            textStyle = Typography.titleSmall,
                            value = pin
                        )

                        val tnc = "Tap to reset."
                        val annotatedString = buildAnnotatedString {
                            append("Forgot your PIN? ")
                            withStyle(style = SpanStyle(color = Color.Red)) {
                                pushStringAnnotation(tag = tnc, annotation = tnc)
                                append(tnc)
                            }
                        }

                        ClickableText(text = annotatedString, onClick = { offset ->
                            annotatedString.getStringAnnotations(offset, offset)
                                .firstOrNull()?.let { span ->
                                    println("Clicked on ${span.item}")
                                }
                        })
                    }
                }
            }
            is RequestState.Error -> {
                Log.d("userProfile", "userProfile: ${userProfile.error}")
            }
        }
    }
}

@Composable
private fun CheckPinOrSetup(
    charLimit: Int,
    anyMutableList: MutableList<String>,
    context: Context,
    onProceedClicked: () -> Unit,
    count: MutableState<Int>,
    pin: MutableState<String>,
    profileViewModel: ProfileViewModel,
    user: UserDTO
) {
    if (user.userPin != null) {
        pin.verifyPin(charLimit, user.userPin!!, anyMutableList, context, onProceedClicked, count)
    } else {
        BlinxHeading(
            if (count.value <= 1) stringResource(R.string.create_pin) else stringResource(R.string.confirm_pin),
            if (count.value <= 1) stringResource(R.string.create_subHeading_pin, charLimit)
            else stringResource(R.string.confirm_subHeader_pin, charLimit)
        )

        pin.SetupPin(charLimit, profileViewModel, anyMutableList, context, onProceedClicked, count)
    }
}


private fun MutableState<String>.verifyPin(
    charLimit: Int,
    userPin: String,
    anyMutableList: MutableList<String>,
    context: Context,
    onProceedClicked: () -> Unit,
    count: MutableState<Int>
) {
    if (value.length == charLimit) {
        count.value++
        anyMutableList.add(value)

        if (anyMutableList.first() == userPin) {
            Toast.makeText(context, WELCOME_BACK_MESSAGE, Toast.LENGTH_SHORT).show()
            onProceedClicked()
        } else {
            Toast.makeText(context, WRONG_PIN_MESSAGE, Toast.LENGTH_SHORT).show()
        }
    }
}

private fun MutableState<String>.SetupPin(
    charLimit: Int,
    profileViewModel: ProfileViewModel,
    anyMutableList: MutableList<String>,
    context: Context,
    onProceedClicked: () -> Unit,
    count: MutableState<Int>
) {
    if (value.length == charLimit) {
        count.value++
        anyMutableList.add(value)

        if (anyMutableList.size == 2 && anyMutableList.first() == anyMutableList.last()) {
            profileViewModel.updateUserProfile(anyMutableList.first())
            Toast.makeText(context, "Pin setup successfully", Toast.LENGTH_SHORT).show()
            onProceedClicked()
        } else {
            count.value = 1
            anyMutableList.clear()
            value = ""
            Toast.makeText(context, "Pin setup failed", Toast.LENGTH_SHORT).show()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PinScreenPreview() {
    PinSetupScreen(onProceedClicked = {})
}
