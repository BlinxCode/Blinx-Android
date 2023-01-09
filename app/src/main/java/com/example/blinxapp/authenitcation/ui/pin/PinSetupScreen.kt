package com.example.blinxapp.authenitcation.ui.pin

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.olajide.pinviewscreen.presentation.PinArea

@Composable
fun PinSetupScreen(
    onProceedClicked: () -> Unit,
) {

    val logPins = PinArea(charLimit = 4)

    Log.d("pinResult", logPins)
}

@Preview(showBackground = true)
@Composable
fun PinScreenPreview(){
    PinSetupScreen(onProceedClicked = {})
}