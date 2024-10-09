package com.android.blinxapp.ui.feature.presentation.components.common.customviews

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.android.blinxapp.ui.feature.presentation.components.common.outlineColors
import com.android.blinxapp.ui.theme.Typography

@Composable
fun InputTextField() {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth().height(50.dp),
        colors = outlineColors(),
        value = text,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        textStyle = Typography.bodyMedium,
        onValueChange = { text = it }
    )
}
