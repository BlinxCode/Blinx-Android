package com.olajide.pinviewscreen.presentation

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun NumberButton(
    modifier: Modifier = Modifier,
    symbol: String,
    textStyle: TextStyle = TextStyle(),
    onClick: () -> Unit,
    ){
    TextButton(onClick = { onClick() },
        modifier = Modifier.size(90.dp)
            .clip(RoundedCornerShape(100.dp))
            .then(modifier)) {
        Text(text = symbol,
            color = systemColor(),
            style = textStyle
        )
    }
}