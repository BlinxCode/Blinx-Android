package com.example.blinxapp.authenitcation.ui.pin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.blinxapp.ui.theme.Typography
import com.example.blinxapp.ui.theme.secondaryGrey

@Composable
fun PinTitleScreen(){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 20.dp, start = 20.dp)) {

        //Setting up Top Text
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {
            //Title modifier
            Text(
                text = "Confirm",
                color = secondaryGrey,
                style = Typography.displayLarge,
                textAlign = TextAlign.Start
            )

            //Description modifier
            Text(
                text = "Passcode",
                style = Typography.displayLarge,
                textAlign = TextAlign.Start
            )
            Spacer(Modifier.size(20.dp))
            //Description modifier
            Text(
                text = "Confirm the 4 digit passcode previously entered to securely sign in to your blinx account",
                style = Typography.titleSmall,
                textAlign = TextAlign.Start
            )

            //Spacer
            Spacer(Modifier.size(20.dp))
        }
    }

}
