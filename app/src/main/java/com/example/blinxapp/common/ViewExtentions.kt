package com.example.blinxapp.common

import android.content.Context
import android.view.Window
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
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.blinxapp.R
import com.example.blinxapp.ui.theme.containerColorBlack
import com.example.blinxapp.ui.theme.containerColorWhite
import com.example.blinxapp.ui.theme.electricBlue
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
fun SystemColorInverse(): Color {
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
            tint = SystemColorInverse()
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

//TopBar setup
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    isGettingStarted: Boolean,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {

    val buttonColors =  TopAppBarDefaults.smallTopAppBarColors(
        containerColor =  MaterialTheme.colorScheme.primary)

    if (!isGettingStarted){
        TopAppBar(
            colors = buttonColors,
            title = {},
            modifier = modifier,
            navigationIcon = {
                if (canNavigateBack){
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBackIosNew,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                }
            }
        )
    }

}
