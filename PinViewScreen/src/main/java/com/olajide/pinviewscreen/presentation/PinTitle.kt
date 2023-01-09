package com.olajide.pinviewscreen.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.olajide.pinviewscreen.ui.theme.Typography

@Composable
fun PinTitleScreen(){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(end = 20.dp, start = 20.dp)) {

        //Setting up Top Text
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {
            //Title modifier
            Text(
                text = "Confirm",
                color = Color.LightGray,
                style = Typography.body1,
                textAlign = TextAlign.Start
            )

            //Description modifier
            Text(
                text = "Passcode",
                style = Typography.body1,
                textAlign = TextAlign.Start

            )
            //Description modifier
            Text(
                text = "Confirm the 4 digit passcode previously entered to securely sign in to your blinx account",
                style = Typography.body2,
                textAlign = TextAlign.Start
            )

            //Spacer
            Spacer(Modifier.size(20.dp))
        }
    }

}
