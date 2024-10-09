package com.android.blinxapp.ui.feature.presentation.authenitcation.ui.pin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.android.blinxapp.ui.theme.Typography
import com.android.blinxapp.ui.theme.secondaryGrey

@Composable
fun BlinxHeading(
    title: String,
    subTitle: String
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp, start = 16.dp, top = 16.dp)) {

        //Setting up Top Text
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {
            //Title modifier
            Text(
                text = title,
                color = secondaryGrey,
                style = Typography.displayLarge,
                textAlign = TextAlign.Start
            )

            Spacer(Modifier.size(40.dp))
            //Description modifier
            Text(
                text = subTitle,
                style = Typography.titleSmall,
                textAlign = TextAlign.Start
            )

            //Spacer
            Spacer(Modifier.size(40.dp))
        }
    }

}
