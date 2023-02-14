package com.android.blinxapp.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.android.blinxapp.ui.theme.*

@Composable
fun PrimaryGreenColor(): ButtonColors {
    return ButtonDefaults.buttonColors(
        containerColor = primaryGreen,
        contentColor = contentColorFor(backgroundColor = white)
    )
}

@Composable
fun YellowColor(): ButtonColors {
    return ButtonDefaults.buttonColors(
        containerColor = yellow,
        contentColor = contentColorFor(backgroundColor = white)
    )
}

@Composable
fun CommonTitle(str1: String, str2: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        //Title modifier
        Text(
            text =str1,
            color = secondaryGrey,
            style = Typography.displayLarge,
            textAlign = TextAlign.Start
        )

        //Description modifier
        Text(
            text = str2,
            modifier = Modifier
                .fillMaxWidth(),
            color = systemColorInverse(),
            style = Typography.displayLarge,
            textAlign = TextAlign.Start

        )

    }
}
