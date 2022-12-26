package com.example.blinxapp.signup

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blinxapp.R
import com.example.blinxapp.common.BlinxStatusBarColor
import com.example.blinxapp.ui.theme.*

class SignupFormScreen : ComponentActivity() {

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

                    MyScaffold()
                }
            }
        }
    }


    // A function which will receive a
// callback to trigger to opening the drawer
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
                        modifier =Modifier.clickable(onClick = onMenuClicked)
                        )

                }
            },
            // background color of topAppBar
            // colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Color.Transparent)
        )
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun MyScaffold() {

        // Create a coroutine scope. Opening of
        // Drawer and snackbar should happen in
        // background thread without blocking main thread
        val coroutineScope = rememberCoroutineScope()

        // Scaffold Composable

        Scaffold(
            modifier = Modifier.fillMaxWidth(),
            // pass the topbar we created
            topBar = {
                TopBar(
                    // When menu is clicked open the
                    // drawer in coroutine scope
                    onMenuClicked = {

                        Toast.makeText(this, "This is a Sample Toast", Toast.LENGTH_LONG).show()
//                        coroutineScope.launch {
//                            // to close use -> scaffoldState.drawerState.close()
//
//                        }
                    })
            },

            // Pass the body in
            // content parameter
            content = {
                MyBody()
            }

        )
    }


    @Composable
    private fun MyBody() {

            val scrollState = rememberScrollState()

            Column(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary)
                .padding(end = 20.dp, start = 20.dp)) {
    //            // Adding a Space of height 100dp
                Spacer(modifier = Modifier.height(60.dp))


                ////////////////////////////////////////////////////////////
                Column(
                    modifier = Modifier
                        .verticalScroll(scrollState)
                        .weight(1f, false)
                ) {
                    //Title modifier
                    Text(
                        text = "Create",
                        color = secondaryGrey,
                        style = Typography.displayLarge,
                        textAlign = TextAlign.Start
                    )

                    //Description modifier
                    Text(
                        text = "your account",
                        style = Typography.displayLarge,
                        textAlign = TextAlign.Start

                    )
                    SignupForm()

                    /////////////////////////////////////////////////////////

                    //Getting started button
                    GettingStartedButton()

                }


        }

    }

    @Composable
    private fun GettingStartedButton() {
        val buttonColors = ButtonDefaults.buttonColors(
            containerColor = primaryGreen,
            contentColor = contentColorFor(backgroundColor = whiteBlinx)
        )

        Column(modifier = Modifier
            .padding(bottom = 40.dp, top = 27.dp)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                onClick = { },
                colors = buttonColors,
                shape = RoundedCornerShape(20)
            ) {
                Text(
                    text = stringResource(R.string.getStarted)
                )
            }

            //Login if already have an account
            LoginText()

        }

    }

    @Composable
    fun LoginText() {
        val annotatedString = buildAnnotatedString {
            append("Already have an account?")
            withStyle(style = SpanStyle(color =primaryGreen,
                fontWeight = FontWeight.Bold)) {
                append(" Log in")
            }
        }

        Text(text = annotatedString,
            modifier = Modifier
            .padding( top = 27.dp),
            style = Typography.labelSmall,
            textAlign = TextAlign.Start
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MyScaffold()
    }
}

