package com.example.blinxapp.signup.email

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.blinxapp.common.TopBar
import com.example.blinxapp.ui.theme.BlinxAppTheme

class ConfirmEmailScreen: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent { 
            BlinxAppTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary
                ) {

                  //  MyScaffold()
                }
            }
        }
    }

//
//    fun MyScaffold() {
//        // Scaffold Composable
//
//        Scaffold(
//            modifier = Modifier.fillMaxWidth(),
//            // pass the topbar we created
//            topBar = {
//                TopBar(
//                    // When menu is clicked open the
//                    // drawer in coroutine scope
//                    onMenuClicked = {
//
//                        Toast.makeText(this, "This is a Sample Toast", Toast.LENGTH_LONG).show()
////                        coroutineScope.launch {
////                            // to close use -> scaffoldState.drawerState.close()
////
////                        }
//                    })
//            },
//
//            // Pass the body in
//            // content parameter
//            content =  {
//                MyBody()
//            }
//
//        )
//    }

    @Composable
    fun MyBody(){}
}