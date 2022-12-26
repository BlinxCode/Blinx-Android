package com.example.blinxapp.onboarding.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.core.content.ContextCompat
import com.example.blinxapp.R
import com.example.blinxapp.ui.theme.*

class OnboardingScreen2 : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Change color status bar.
        window.statusBarColor = ContextCompat.getColor(this, R.color.lightGrey)
        setContent {
            BlinxAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    ScreenContent()
                }
            }
        }
    }

    @Composable
    fun ScreenContent(){
        OnboardingItems()
        }

    @Composable
    fun OnboardingItems(

    ) {
        Box() {

//            // Adding a Space of height 100dp
//            Spacer(modifier = Modifier.height(100.dp))
//

        }

        ConstraintLayout(
            modifier = Modifier.fillMaxSize(),
        ) {

            //Declaring a reference
            val (backgroundImg, gradientBackground, columnViews) = createRefs()

            //Creating a background Image
            Image(
                painter = painterResource(id = R.drawable.blinx_background3),
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
                    .height(600.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, Color.White),
                            0f,  // TODO: set start
                            650f,  // TODO: set end
                        )
                    )) {
            }

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
                        .padding( start = 20.dp, end = 20.dp)
                        .fillMaxWidth(),
                    color = electricBlue,
                    style = Typography.displayLarge,
                    textAlign = TextAlign.Start

                )

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
            .padding(bottom = 40.dp, end = 20.dp, start = 20.dp, top = 27.dp)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Button(
                modifier = Modifier.width(350.dp)
                    .height(60.dp),
                onClick = { },
                colors = buttonColors,
                shape = RoundedCornerShape(20)
            ) {
                Text(
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
            color = electricBlue,
            style = Typography.labelMedium,
            textAlign = TextAlign.Start
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ScreenContent()
    }
}

