package com.android.blinxapp.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun DashboardScreen() {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.spacedBy(10.dp)) {

        Text(text = "Hello Dashboard")
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview(){
    DashboardScreen()
}