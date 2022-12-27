package com.example.blinxapp.common

import android.content.Context
import android.view.Window
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.blinxapp.R
import com.example.blinxapp.ui.theme.containerColorBlack
import com.example.blinxapp.ui.theme.containerColorWhite
import com.example.blinxapp.ui.theme.primaryGreen


// Setting input field property to conform with light and dark mode
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun outlineColors(): TextFieldColors {
    var colorStr =  if (isSystemInDarkTheme()){
        TextFieldDefaults.outlinedTextFieldColors(focusedLabelColor = Color.White,
            focusedBorderColor = primaryGreen, cursorColor = Color.White, containerColor = containerColorBlack, unfocusedBorderColor = containerColorBlack )
    }else{
        TextFieldDefaults.outlinedTextFieldColors(focusedLabelColor = Color.Black,
            focusedBorderColor = primaryGreen, cursorColor = Color.Black, containerColor = containerColorWhite, unfocusedBorderColor = containerColorWhite  )
    }
    return colorStr
}

// Return color state based on SystemInDarkTheme
@Composable
fun sytemColor(): Color {
    var colorStr =  if (isSystemInDarkTheme()){
        Color.White
    }else{
        Color.Black
    }
    return colorStr
}

@Composable
fun PasswordIconSetup(passwordVisibility: MutableState<Boolean>) {
    IconButton(onClick = {
        passwordVisibility.value = !passwordVisibility.value
    }) {
        Icon(
            imageVector = if (passwordVisibility.value) Icons.Default.VisibilityOff else Icons.Default.Visibility,
            contentDescription = "visibility",
            tint = sytemColor()
        )
    }
}


@Composable
fun BlinxStatusBarColor(window: Window, context: Context) {
    if (isSystemInDarkTheme()) {
        window.statusBarColor = ContextCompat.getColor(context, R.color.black)
    } else {
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars =
            true
        window.statusBarColor = ContextCompat.getColor(context, R.color.white)
    }
}

// A function which will receive a
// callback to trigger to go previous page
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onMenuClicked: () -> Unit) {
    val buttonColors = TopAppBarDefaults.mediumTopAppBarColors(
        containerColor =  Color.Transparent
    )
    // TopAppBar Composable
    TopAppBar(
        // Provide Title
        colors = buttonColors,
        title = {},
        // Provide the navigation Icon (Icon on the left to toggle drawer)
        navigationIcon = {

            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBackIosNew,
                    contentDescription = "Arrow Back",
                    modifier = Modifier.clickable(onClick = onMenuClicked)
                )

            }
        },
        // background color of topAppBar
        // colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Color.Transparent)
    )
}
