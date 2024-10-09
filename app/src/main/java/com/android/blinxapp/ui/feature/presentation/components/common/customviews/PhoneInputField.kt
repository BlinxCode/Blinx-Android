package com.android.blinxapp.ui.feature.presentation.components.common.customviews

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.android.blinxapp.ui.feature.presentation.components.common.outlineColors
import com.android.blinxapp.ui.theme.Typography
import com.android.blinxapp.ui.theme.grey

@Composable
fun PhoneInputField(modifier: Modifier = Modifier,
                    placeholder: String = "",
                    onTextChange: ((String) -> Unit)? = null) {

    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        modifier = modifier.fillMaxWidth().height(50.dp),
        colors = outlineColors(),
        value = text,
        textStyle = Typography.bodyMedium,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        onValueChange = {
            text = it
            onTextChange?.invoke(it)
        },
        placeholder = {
            Text( text = placeholder,
                style = Typography.bodyMedium,
                color = grey
            )
        }
    )
}