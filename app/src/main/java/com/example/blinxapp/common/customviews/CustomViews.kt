package com.example.blinxapp.common.customviews

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.blinxapp.ui.theme.Typography
import com.example.blinxapp.ui.theme.primaryGreen
import com.example.blinxapp.ui.theme.whiteBlinx


@Composable
fun ContinueButtonButton(onProceedClicked: () -> Unit, stringResource: String) {
    Spacer(Modifier.size(32.dp))
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = primaryGreen,
        contentColor = contentColorFor(backgroundColor = whiteBlinx)
    )
    Column(modifier = Modifier
        .padding(bottom = 40.dp)
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            onClick = { onProceedClicked()},
            colors = buttonColors,
            shape = RoundedCornerShape(20)
        ) {
            Text(
                text = stringResource,
                style = Typography.labelSmall,
            )
        }
    }
}