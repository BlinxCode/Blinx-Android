package com.android.blinxapp.feature.presentation.authenitcation.ui.pin

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
import androidx.compose.material3.Text
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
import com.android.blinxapp.core.RequestState
import com.android.blinxapp.domain.model.UserDTO
import com.android.blinxapp.feature.presentation.components.ProgressBar
import com.android.blinxapp.feature.viewmodel.ProfileViewModel
import com.android.blinxapp.ui.theme.Typography
import com.android.blinxapp.ui.theme.secondaryGrey
import com.olajide.pinviewscreen.presentation.ComposablePinView

@Composable
fun PinSetupScreen(onProceedClicked: () -> Unit) {

    val profileViewModel: ProfileViewModel = hiltViewModel()
    var isLoading by remember { mutableStateOf(true) }

    val pin = remember { mutableStateOf("") }
    val count = remember { mutableIntStateOf(1) }
    val context = LocalContext.current
    val charLimit = 4
    val anyMutableList = remember { mutableListOf("") }

    LaunchedEffect(Unit) {
        profileViewModel.getUserProfileInDB()
        anyMutableList.clear()
    }

    Box(modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ){

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
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Log.d("userProfile1", "userProfile: ${user.displayName}")
                            //Setup Pin Data and title
                            CheckPinInDB(charLimit, anyMutableList,
                                context, onProceedClicked,
                                count,
                                pin,
                                profileViewModel,
                                user)
                            //Setup Pin View
                            ComposablePinView(
                                charLimit = charLimit,
                                text = "",
                                textStyle = Typography.titleSmall, value = pin
                            )

                            val tnc = "Tap to reset."
                            val annotatedString = buildAnnotatedString {
                                append("Forgot your PIN? ")
                                withStyle(style = SpanStyle(color = Color.Red), ) {
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
                is RequestState.Error -> userProfile.error.let { error ->
                    Log.d("userProfile", "userProfile: $error")
                }
            }
    }
}

@Composable
private fun CheckPinInDB(
    charLimit: Int,
    anyMutableList: MutableList<String>,
    context: Context,
    onProceedClicked: () -> Unit,
    count: MutableIntState,
    pin: MutableState<String>,
    profileViewModel: ProfileViewModel,
    user: UserDTO
) {
    if (user.userPin != null) {

        charLimit.VerifyPin(
            userPin = user.userPin!!,
            anyMutableList = anyMutableList,
            context = context,
            onProceedClicked = onProceedClicked,
            count = count, pin = pin
        )

    } else {
        BlinxHeading(
            (if (count.intValue <= 1) {
                stringResource(R.string.create_pin)
            } else {
                stringResource(R.string.confirm_pin)
            }),
            (if (count.intValue <= 1) {
                stringResource(R.string.create_subHeading_pin, charLimit)
            } else {
                stringResource(R.string.confirm_subHeader_pin, charLimit)
            }))

        charLimit.SetupPin(
            profileViewModel = profileViewModel,
            anyMutableList = anyMutableList,
            context = context,
            onProceedClicked = onProceedClicked,
            count = count, pin = pin
        )
    }
}

@Composable
private fun Int.VerifyPin(
    userPin: String,
    anyMutableList: MutableList<String>,
    context: Context,
    onProceedClicked: () -> Unit,
    count: MutableState<Int>,
    pin: MutableState<String>
) {
    if (pin.value.length == this) {
        count.value++
        Log.d("LogSizeX", count.value.toString())
        anyMutableList.add(pin.value)

        Log.d("ExisitingPIn", userPin)
        Log.d("InputPin", anyMutableList.first().toString())
        if (anyMutableList.first().contentEquals(userPin)) {
            LaunchedEffect(Unit) {
                Toast.makeText(context, "Welcome back!", Toast.LENGTH_SHORT).show()
                onProceedClicked()
            }
        } else {
            Toast.makeText(context, "Wrong Pin", Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
private fun Int.SetupPin(
    profileViewModel: ProfileViewModel,
    anyMutableList: MutableList<String>,
    context: Context,
    onProceedClicked: () -> Unit,
    count: MutableState<Int>,
    pin: MutableState<String>
) {
    if (pin.value.length == this) {
        count.value++
        Log.d("LogSizeX", count.value.toString())
        anyMutableList.add(pin.value)

        if (anyMutableList.size == 2) {
            if (anyMutableList.first().contentEquals(anyMutableList.last())) {
                LaunchedEffect(Unit) {
                    profileViewModel.updateUserProfile(anyMutableList.first())
                    Toast.makeText(context, "Pin setup successfully", Toast.LENGTH_SHORT).show()
                    onProceedClicked()
                }
            } else {
                count.value = 1
                anyMutableList.clear()
                pin.value = ""
                Toast.makeText(context, "Pin setup failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PinScreenPreview() {
    PinSetupScreen(onProceedClicked = {})
}
