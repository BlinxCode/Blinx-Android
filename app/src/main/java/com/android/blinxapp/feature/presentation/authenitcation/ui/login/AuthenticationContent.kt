package com.android.blinxapp.feature.presentation.authenitcation.ui.login

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.android.blinxapp.R
import com.android.blinxapp.feature.presentation.components.common.CommonTitle
import com.android.blinxapp.feature.presentation.components.common.systemColorInverse
import com.android.blinxapp.ui.theme.*

    @Composable
    fun AuthenticationContent(
        loadingState: MutableState<Boolean>,
        onButtonClicked: () -> Unit
    ) {

            ConstraintLayout( modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)) {

                //Declaring a child reference id
                val (backgroundImg, gradientBackground, columnViews) = createRefs()

                //Creating a background Image
                Image(
                    painter = painterResource(id = R.drawable.blinx_black_background3.takeIf
                    { isSystemInDarkTheme() } ?: R.drawable.blinx_background3),
                    contentDescription = "Blinx offers",
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(backgroundImg) {
                            top.linkTo(parent.top)
                        }
                )


                // Creating a sample Row and setting is
                // background with above Gradient Color
                Row(
                    Modifier
                        .fillMaxWidth()
                        .constrainAs(gradientBackground) {
                            bottom.linkTo(parent.bottom)
                        }
                        .height(500.dp)
                        .background(
                            if (isSystemInDarkTheme()) {
                                Brush.verticalGradient(
                                    listOf(Color.Transparent, Color.Black),
                                    0f,
                                    750f,
                                )
                            } else {
                                Brush.verticalGradient(
                                    listOf(Color.Transparent, Color.White),
                                    0f,
                                    750f,
                                )
                            }

                        )) {}

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp, start = 20.dp)
                        .constrainAs(columnViews) {
                            bottom.linkTo(parent.bottom)
                        }
                ) {

                    CommonTitle( "Blinx offers","Seamless payment automations")
                    //Getting started button
                    GettingStartedButton(loadingState, onButtonClicked)



                }
            }
    }

@Composable
    private fun GettingStartedButton(loadingState: MutableState<Boolean>, onButtonClicked: ()-> Unit) {

        Column(modifier = Modifier
            .padding(bottom = 40.dp, top = 27.dp)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                GoogleButton(
                    loadingState = loadingState.value,
                    onClick = onButtonClicked
                )
            }

        }
    }

@Composable
    fun LoginText(onLoginButtonClicked: ()-> Unit) {

        Spacer(modifier =Modifier.padding(top = 50.dp))
        val annotatedString = buildAnnotatedString {
            append("Already have an account?")
            withStyle(style = SpanStyle(color =  primaryGreen,
                fontWeight = FontWeight.Bold)) {
                append(" Log in")
            }
        }

        Text(text = annotatedString,
            modifier = Modifier.clickable(enabled = true) {
                onLoginButtonClicked()
            },

            color = systemColorInverse(),
            style = Typography.labelSmall,
            textAlign = TextAlign.Start
        )
    }

    @SuppressLint("UnrememberedMutableState")
    @Preview(showBackground = true)
    @Composable
    fun GettingStartedPreview() {
        AuthenticationContent(loadingState = mutableStateOf(true), onButtonClicked = {})
    }