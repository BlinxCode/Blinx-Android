package com.android.blinxapp.authenitcation.ui.get_started

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.android.blinxapp.R
import com.android.blinxapp.common.CommonTitle
import com.android.blinxapp.common.PrimaryGreenColor
import com.android.blinxapp.common.systemColorInverse
import com.android.blinxapp.ui.theme.*


    @Composable
    fun GettingStartedScreen(
        onGetStartedButtonClicked: () -> Unit,
        onLoginButtonClicked: () -> Unit = {},
    ) {

            ConstraintLayout( modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary)) {

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
                        .fillMaxWidth().padding(end = 20.dp, start = 20.dp)
                        .constrainAs(columnViews) {
                            bottom.linkTo(parent.bottom)
                        }
                ) {

                    CommonTitle( "Blinx offers","Seamless payment automations")
                    //Getting started button
                    GettingStartedButton(onGetStartedButtonClicked, onLoginButtonClicked)



                }
            }
    }

@Composable
    private fun GettingStartedButton(onGetStartedButtonClicked: () -> Unit, onLoginButtonClicked: ()-> Unit) {

        Column(modifier = Modifier
            .padding(bottom = 40.dp, top = 27.dp)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Button(
                modifier = Modifier.fillMaxWidth()
                    .height(50.dp),
                onClick = {onGetStartedButtonClicked() },
                colors = PrimaryGreenColor(),
                shape = RoundedCornerShape(20)
            ) {
                Text(
                    style = Typography.labelSmall,
                    text = stringResource(R.string.getStarted),
                    color = white
                )
            }

            //Login if already have an account
        LoginText(onLoginButtonClicked)
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

    @Preview(showBackground = true)
    @Composable
    fun GettingStartedPreview() {
        GettingStartedScreen(onLoginButtonClicked = {}, onGetStartedButtonClicked = {})
    }