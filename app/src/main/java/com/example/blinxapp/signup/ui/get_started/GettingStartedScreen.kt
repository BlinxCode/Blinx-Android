package com.example.blinxapp.signup.ui.get_started

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.example.blinxapp.R
import com.example.blinxapp.common.SystemColorInverse
import com.example.blinxapp.ui.theme.*


    @Composable
    fun GettingStartedScreen(
        onGetStartedButtonClicked: () -> Unit,
        onLoginButtonClicked: () -> Unit = {},
        modifier: Modifier = Modifier
    ) {

            ConstraintLayout( modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary)) {

                //Declaring a reference
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
                                    0f,  // TODO: set start
                                    750f,  // TODO: set end
                                )
                            } else {
                                Brush.verticalGradient(
                                    listOf(Color.Transparent, Color.White),
                                    0f,  // TODO: set start
                                    750f,  // TODO: set end
                                )
                            }

                        )) {}

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(columnViews) {
                            bottom.linkTo(parent.bottom)
                        }
                ) {
                    //Title modifier
                    Text(
                        text = "Blinx offers",
                        modifier = Modifier.padding(end = 20.dp, start = 20.dp),
                        color = secondaryGrey,
                        style = Typography.displayLarge,
                        textAlign = TextAlign.Start
                    )

                    //Description modifier
                    Text(
                        text = "Seamless payment automations",
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp)
                            .fillMaxWidth(),
                        color = SystemColorInverse(),
                        style = Typography.displayLarge,
                        textAlign = TextAlign.Start

                    )

                    //Getting started button
                    GettingStartedButton(onGetStartedButtonClicked)

                }
            }
    }

    @Composable
    private fun GettingStartedButton(onGetStartedButtonClicked: () -> Unit) {
        val buttonColors = ButtonDefaults.buttonColors(
            containerColor = primaryGreen,
            contentColor = contentColorFor(backgroundColor = whiteBlinx)
        )

        Column(modifier = Modifier
            .padding(bottom = 40.dp, end = 20.dp, start = 20.dp, top = 27.dp)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Button(
                modifier = Modifier
                    .width(350.dp)
                    .height(56.dp),
                onClick = {onGetStartedButtonClicked() },
                colors = buttonColors,
                shape = RoundedCornerShape(20)
            ) {
                Text(
                    style = Typography.labelSmall,
                    text = stringResource(R.string.getStarted),
                    color = whiteBlinx
                )
            }

            //Login if already have an account
        LoginText()
        }
    }

    @Composable
    fun LoginText() {

        Spacer(modifier =Modifier.padding(top = 50.dp))
        val annotatedString = buildAnnotatedString {
            append("Already have an account?")
            withStyle(style = SpanStyle(color =  primaryGreen,
                fontWeight = FontWeight.Bold)) {
                append(" Log in")
            }
        }

        Text(text = annotatedString,
            modifier = Modifier,
            color = SystemColorInverse(),
            style = Typography.labelSmall,
            textAlign = TextAlign.Start
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GettingStartedPreview() {
        GettingStartedScreen(onLoginButtonClicked = {}, onGetStartedButtonClicked = {})
    }