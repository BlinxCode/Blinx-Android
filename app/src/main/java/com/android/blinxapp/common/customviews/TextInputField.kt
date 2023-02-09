package com.android.blinxapp.common.customviews

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.blinxapp.common.outlineColors
import com.android.blinxapp.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTextField() {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth().height(50.dp),
        colors = outlineColors(),
        value = text,
        textStyle = Typography.bodyMedium,
        onValueChange = { text = it }
    )
}
