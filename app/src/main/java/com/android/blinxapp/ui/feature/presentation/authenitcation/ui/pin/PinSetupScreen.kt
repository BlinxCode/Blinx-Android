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
import com.android.blinxapp.core.RequestState
import com.android.blinxapp.domain.model.UserDTO
import com.android.blinxapp.ui.feature.presentation.components.ProgressBar
import com.android.blinxapp.ui.feature.viewmodel.ProfileViewModel
import com.android.blinxapp.ui.theme.Typography
import com.olajide.pinviewscreen.presentation.ComposablePinView

/**
 * Composable function for the PIN setup screen.
 * @param onProceedClicked Callback to be triggered when proceeding after the PIN is verified or set.
 */
@Composable
fun PinSetupScreen(profileViewModel: ProfileViewModel = hiltViewModel(),
                   onProceedClicked: () -> Unit) {

    // State to track loading status
    var isLoading by remember { mutableStateOf(true) }

    // PIN value state
    val pin = remember { mutableStateOf("") }

    // Counter for PIN creation steps
    val count = remember { mutableIntStateOf(1) }

    // Retrieve the context for UI operations like Toast
    val context = LocalContext.current

    // Limit the PIN to 4 characters
    val charLimit = 4

    // Mutable list to store entered PINs
    val anyMutableList = remember { mutableListOf("") }

    // Load user profile on screen launch
    LaunchedEffect(Unit) {
        profileViewModel.getUserProfileInDB()
        anyMutableList.clear() // Clear the list when screen launches
    }

    // Main container for the screen
    Box(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        // Display a progress bar when loading
        ProgressBar(isLoading)

        // Handle the different request states for the user profile
        when (val userProfile = profileViewModel.getUserProfileResponse) {
            is RequestState.Loading -> {
                Log.d("userProfile1", "is loading")
                isLoading = true
            }
            is RequestState.Success -> userProfile.data?.let { user ->
                profileViewModel.userProfile = user
                isLoading = false

                // Proceed if the user's display name is available
                if (!user.displayName.isNullOrEmpty()) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Log.d("userProfile1", "userProfile: ${user.displayName}")

                        // Check or Set up the PIN depending on user data
                        CheckPinInDB(
                            charLimit, anyMutableList, context, onProceedClicked, count, pin, profileViewModel, user
                        )

                        // PIN input view
                        ComposablePinView(
                            charLimit = charLimit,
                            text = "",
                            textStyle = Typography.titleSmall,
                            value = pin
                        )

                        // Link for resetting the PIN
                        val tnc = "Tap to reset."
                        val annotatedString = buildAnnotatedString {
                            append("Forgot your PIN? ")
                            withStyle(style = SpanStyle(color = Color.Red)) {
                                pushStringAnnotation(tag = tnc, annotation = tnc)
                                append(tnc)
                            }
                        }

                        // Make the text clickable
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
                // Log error in case of failure
                Log.d("userProfile", "userProfile: ${userProfile.error}")
            }
        }
    }
}

/**
 * Function to check if the user already has a PIN in the database.
 * If yes, verify the PIN, otherwise guide the user to create one.
 */
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
        // User has a PIN; verify it
        charLimit.VerifyPin(
            userPin = user.userPin!!, anyMutableList, context, onProceedClicked, count, pin
        )
    } else {
        // User does not have a PIN; set it up
        BlinxHeading(
            if (count.intValue <= 1) stringResource(R.string.create_pin) else stringResource(R.string.confirm_pin),
            if (count.intValue <= 1) stringResource(R.string.create_subHeading_pin, charLimit)
            else stringResource(R.string.confirm_subHeader_pin, charLimit)
        )

        charLimit.SetupPin(profileViewModel, anyMutableList, context, onProceedClicked, count, pin)
    }
}

/**
 * Extension function to verify the entered PIN against the stored PIN.
 */
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
        anyMutableList.add(pin.value)

        // Compare the input PIN with the stored PIN
        if (anyMutableList.first().contentEquals(userPin)) {
            // PIN is correct, proceed
            LaunchedEffect(Unit) {
                Toast.makeText(context, "Welcome back!", Toast.LENGTH_SHORT).show()
                onProceedClicked()
            }
        } else {
            // Wrong PIN, notify user
            Toast.makeText(context, "Wrong Pin", Toast.LENGTH_SHORT).show()
        }
    }
}

/**
 * Extension function to set up a new PIN for the user.
 */
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
        anyMutableList.add(pin.value)

        // Verify the PIN setup
        if (anyMutableList.size == 2) {
            if (anyMutableList.first().contentEquals(anyMutableList.last())) {
                // Pins match, save the PIN
                LaunchedEffect(Unit) {
                    profileViewModel.updateUserProfile(anyMutableList.first())
                    Toast.makeText(context, "Pin setup successfully", Toast.LENGTH_SHORT).show()
                    onProceedClicked()
                }
            } else {
                // Pins do not match, reset the process
                count.value = 1
                anyMutableList.clear()
                pin.value = ""
                Toast.makeText(context, "Pin setup failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

/**
 * Preview for the PIN setup screen.
 */
@Preview(showBackground = true)
@Composable
fun PinScreenPreview() {
    PinSetupScreen(onProceedClicked = {})
}
