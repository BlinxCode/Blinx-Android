package com.olajide.pinviewscreen.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.olajide.pinviewscreen.data.PinActions

@Composable
fun PinButtons(
    onActions: (PinActions) -> Unit
) {

    var characters = ""
    var pointerPosition: Int
    val rowDp = 70.dp
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        NumberButton(
            modifier = Modifier,
            "1",
            TextStyle.Default,
            onClick = {onActions(PinActions.Number("1"))

                Log.d("LogPInViewModel","1" )}
        )
        NumberButton(
            modifier = Modifier,
            "2",
            TextStyle.Default,
            onClick = {onActions(PinActions.Number("2"))}
        )
        NumberButton(
            modifier = Modifier,
            "3",
            TextStyle.Default,
            onClick = {onActions(PinActions.Number("3"))}
        )

    }

    //Row 2

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        NumberButton(
            modifier = Modifier,
            "4",
            TextStyle.Default,
            onClick = {onActions(PinActions.Number("4"))}
        )
        NumberButton(
            modifier = Modifier,
            "5",
            TextStyle.Default,
            onClick = {onActions(PinActions.Number("5"))}
        )
        NumberButton(
            modifier = Modifier,
            "6",
            TextStyle.Default,
            onClick = {onActions(PinActions.Number("6"))}
        )
    }

    //Row 3

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        NumberButton(
            modifier = Modifier,
            "7",
            TextStyle.Default,
            onClick = {onActions(PinActions.Number("7"))}
        )
        NumberButton(
            modifier = Modifier,
            "8",
            TextStyle.Default,
            onClick = {onActions(PinActions.Number("8"))}
        )
        NumberButton(
            modifier = Modifier,
            "9",
            TextStyle.Default,
            onClick = {onActions(PinActions.Number("9"))}
        )
    }

    //Row 2

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
    ) {

        NumberButton(
            modifier = Modifier,
            "<",
            TextStyle.Default,
            onClick = {onActions(PinActions.BackSpace)}
        )
        NumberButton(
            modifier = Modifier,
            "0",
            TextStyle.Default,
            onClick = {onActions(PinActions.Number("0"))}
        )
        NumberButton(
            modifier = Modifier,
            "",
            TextStyle.Default,
            onClick = {}
        )
    }
}
