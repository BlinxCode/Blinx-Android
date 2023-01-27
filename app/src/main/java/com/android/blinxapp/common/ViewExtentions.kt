package com.android.blinxapp.common

import android.content.Context
import android.content.res.Configuration
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
import com.android.blinxapp.ui.theme.containerColorBlack
import com.android.blinxapp.ui.theme.containerColorWhite
import com.android.blinxapp.ui.theme.electricBlue
import com.android.blinxapp.ui.theme.primaryGreen
import com.android.blinxapp.R

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
fun cardColors(): Color {
    return   containerColorBlack.takeIf  { isSystemInDarkTheme() }?:Color.White
}

@Composable
fun CardTitleColors(): Color {
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
