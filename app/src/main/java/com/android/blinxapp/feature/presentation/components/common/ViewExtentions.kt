package com.android.blinxapp.feature.presentation.components.common

import android.content.Context
import android.content.res.Configuration
import android.view.Window
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.android.blinxapp.R
import com.android.blinxapp.ui.theme.*

//NonComposable functions
fun Context.isSystemInDarkTheme(): Boolean =
    (resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)) == Configuration.UI_MODE_NIGHT_YES

// Setting input field property to conform with light and dark mode
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun outlineColors(): TextFieldColors {
    val colorStr =  if (isSystemInDarkTheme()){
        TextFieldDefaults.outlinedTextFieldColors(focusedLabelColor = Color.White,
            focusedBorderColor = primaryGreen, cursorColor = Color.White, containerColor = containerColorBlack, unfocusedBorderColor = containerColorBlack )
    }else{
        TextFieldDefaults.outlinedTextFieldColors(focusedLabelColor = Color.Black,
            focusedBorderColor = primaryGreen, cursorColor = Color.Black, containerColor = containerColorWhite, unfocusedBorderColor = containerColorWhite  )
    }
    return colorStr
}

@Composable
fun primaryCardColors(): Color {
    return   containerColorBlack.takeIf  { isSystemInDarkTheme() }?:Color.White
}

@Composable
fun cardTitleColors(): Color {
    return  Color.White.takeIf { isSystemInDarkTheme() }?: electricBlue
}


// Return color state based on SystemInDarkTheme
@Composable
fun systemColorInverse(): Color {
    return  Color.White.takeIf { isSystemInDarkTheme() }?: electricBlue
}

@Composable
fun PasswordIconSetup(passwordVisibility: MutableState<Boolean>) {
    IconButton(onClick = {
        passwordVisibility.value = !passwordVisibility.value
    }) {
        Icon(
            imageVector = Icons.Default.VisibilityOff.takeIf {
                passwordVisibility.value }?:  Icons.Default.Visibility,
            contentDescription = "visibility",
            tint = systemColorInverse()
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

//Modalshet custom color settings
@Composable
fun modalsheetColor():Color {
    return  primaryGreen.takeIf { isSystemInDarkTheme() }?: containerColorWhite
}

@Composable
fun paddingValues():PaddingValues {
    return PaddingValues(
        start = 16.dp,
        end = 16.dp
    )
}