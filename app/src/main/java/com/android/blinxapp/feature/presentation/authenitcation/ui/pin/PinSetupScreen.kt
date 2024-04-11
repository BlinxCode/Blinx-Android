package com.android.blinxapp.feature.presentation.authenitcation.ui.pin

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.blinxapp.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.blinxapp.core.RequestState
import com.android.blinxapp.domain.model.UserDTO
import com.android.blinxapp.feature.presentation.components.ProgressBar
import com.android.blinxapp.feature.presentation.viewmodel.ProfileViewModel
import com.android.blinxapp.ui.theme.Typography
import com.olajide.pinviewscreen.presentation.ComposablePinView

@Composable
fun PinSetupScreen(onProceedClicked: () -> Unit) {

    val profileViewModel: ProfileViewModel = hiltViewModel()

    val pin = remember { mutableStateOf("") }
    val count = remember { mutableIntStateOf(1) }
    val context = LocalContext.current
    val charLimit = 4
    val anyMutableList = remember { mutableListOf("") }
    LaunchedEffect(Unit) {
        profileViewModel.getUserProfileInDB()
        anyMutableList.clear()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {

        when (val userProfile = profileViewModel.getUserProfileResponse) {
            is RequestState.Loading -> ProgressBar()
            is RequestState.Success -> userProfile.data?.let { user ->
                profileViewModel.userProfile = user
                if (!user.displayName.isNullOrEmpty()) {
                    Log.d("userProfile1", "userProfile: ${user.displayName}")
                    //Setup Pin Data and title
                    CheckPinInDB(
                        charLimit,
                        anyMutableList,
                        context,
                        onProceedClicked,
                        count,
                        pin,
                        profileViewModel,
                        user
                    )

                    //Setup Pin View
                    ComposablePinView(
                        charLimit = charLimit,
                        text = "Passcode",
                        textStyle = Typography.titleSmall, value = pin
                    )
                }

                }


            is RequestState.Error -> userProfile.error.let { error ->
                Log.d("userProfile", "userProfile: ${error}")
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
        BlinxHeading(
            stringResource(R.string.enter_pin),
            stringResource(R.string.enter_subHeading_pin, charLimit)
        )

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
            })
        )

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
