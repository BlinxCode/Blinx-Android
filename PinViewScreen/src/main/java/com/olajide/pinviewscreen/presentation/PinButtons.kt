package com.olajide.pinviewscreen.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.olajide.pinviewscreen.data.PinActions

@Composable
fun PinButtons(
    style: TextStyle = TextStyle(),
    onActions: (PinActions) -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        NumberButton(

            modifier = Modifier,
            "1",
            style,
            onClick = {
                onActions(PinActions.Number("1"))
            }
        )
        NumberButton(
            modifier = Modifier,
            "2",
            style,
            onClick = {onActions(PinActions.Number("2"))},

        )
        NumberButton(
            modifier = Modifier,
            "3",
            style,
            onClick = {onActions(PinActions.Number("3"))},

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
            style,
            onClick = {onActions(PinActions.Number("4"))},

        )
        NumberButton(
            modifier = Modifier,
            "5",
            style,
            onClick = {onActions(PinActions.Number("5"))},
        )
        NumberButton(
            modifier = Modifier,
            "6",
            style,
            onClick = {onActions(PinActions.Number("6"))},
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
            style,
            onClick = {onActions(PinActions.Number("7"))},
        )
        NumberButton(
            modifier = Modifier,
            "8",
            style,
            onClick = {onActions(PinActions.Number("8"))},
        )
        NumberButton(
            modifier = Modifier,
            "9",
            style,
            onClick = {onActions(PinActions.Number("9"))},
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
            style,
            onClick = {onActions(PinActions.BackSpace)},
        )
        NumberButton(
            modifier = Modifier,
            "0",
            style,
            onClick = {onActions(PinActions.Number("0"))},
        )
        NumberButton(
            modifier = Modifier,
            "",
            style,
            onClick = {},
        )
    }
}
