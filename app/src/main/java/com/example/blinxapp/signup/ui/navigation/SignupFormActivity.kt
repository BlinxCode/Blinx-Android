package com.example.blinxapp.signup.ui.navigation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import com.example.blinxapp.common.BlinxStatusBarColor
import com.example.blinxapp.ui.theme.*

class SignupFormActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Change color status bar.
        setContent {
            BlinxAppTheme {
                BlinxStatusBarColor(window, this)
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary
                ) {
                   SignupNavigation()
                }
            }
        }
    }
}

