package com.android.blinxapp.feature.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.android.blinxapp.ui.theme.primaryGreen

@Composable
fun ProgressBar(
        isVisible: Boolean
    ) {
        if (isVisible) {
            Box(
                modifier = Modifier.size(30.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = primaryGreen,
                    strokeWidth = 4.dp
                )
            }
        }
}