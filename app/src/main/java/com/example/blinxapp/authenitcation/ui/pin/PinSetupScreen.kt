package com.example.blinxapp.authenitcation.ui.pin

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blinxapp.ui.theme.Typography
import com.olajide.pinviewscreen.presentation.PinArea

@Composable
fun PinSetupScreen(onProceedClicked: () -> Unit ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
            verticalArrangement = Arrangement.spacedBy(10.dp)) {
            PinTitleScreen()
            val logPins = PinArea(charLimit = 4, textStyle = Typography.titleSmall)
            Log.d("pinResult", logPins)
        }
}

@Preview(showBackground = true)
@Composable
fun PinScreenPreview(){
    PinSetupScreen(onProceedClicked = {})
}