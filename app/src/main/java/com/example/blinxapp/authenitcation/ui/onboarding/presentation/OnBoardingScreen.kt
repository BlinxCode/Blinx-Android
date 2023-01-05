package com.example.blinxapp.authenitcation.ui.onboarding.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.blinxapp.R
import com.example.blinxapp.authenitcation.ui.onboarding.data.model.OnBoardingData
import com.example.blinxapp.authenitcation.ui.onboarding.common.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState


    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun OnBoardingScreen(
        onNextButtonClicked: () -> Unit
    ){

        Box(){

            //Create a list of items to display in pager.
            val items = ArrayList<OnBoardingData>()
            items.add(
                OnBoardingData(
                    R.drawable.blinx_background0,
                    "Fund wallet",

                    "Create and fund a wallet of your choice"
                )
            )
            items.add(
                OnBoardingData(
                    R.drawable.blinx_background1,
                    "Pay bills",
                    "Automation your payments"
                )
            )
            items.add(
                OnBoardingData(
                    R.drawable.blinx_background2,
                    "Save and Invest",
                    "Start saving early and invest regularly"
                )
            )


            //Call rememberPageState Function
            val pagerState = rememberPagerState(initialPage = 0)
            OnBoardingPager(
                onNextButtonClicked,
                item = items,
                pagerState = pagerState
            )
        }


    }

    @ExperimentalPagerApi
    @Composable
    fun OnBoardingPager(
        onNextButtonClicked: () -> Unit,
        item: List<OnBoardingData>,
        pagerState: PagerState
    ) {

        ConstraintLayout(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)) {
            // Creating a sample Row and setting is
            // background with above Gradient Color
            //Declaring a reference
            val (backgroundImg, gradientBackground, columnViews, pageImages, pageMessages ) = createRefs()

            ImageWithBackground(
                backgroundDrawableResId = R.drawable.blinx_logo,
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(backgroundImg) {
                    }
            )

            //Show full screen of onboarding items
            Column(horizontalAlignment = Alignment.CenterHorizontally,  modifier = Modifier
                .fillMaxWidth()
                .constrainAs(pageImages) {
                    bottom.linkTo(parent.bottom)
                }) {
                HorizontalPager(state = pagerState, count = 3) { page ->

                    // A function that house arrangement of views for the PageList to display
                    OnboardingItems(item, page)
                }
            }
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

                    )) {

            }

            //Show screen messages
            PagerTexts(item, pagerState.currentPage,
                modifier = Modifier.constrainAs(pageMessages) {
                bottom.linkTo(parent.bottom)
            }.padding(bottom = 100.dp).fillMaxWidth())


            //Page indication horizontal to next button
            Row(
                    modifier = Modifier
                        .padding(bottom = 40.dp, end = 20.dp, start = 20.dp)
                        .fillMaxWidth()
                        .constrainAs(columnViews) {
                            bottom.linkTo(parent.bottom)
                        },
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    PagerIndicator(item.size, pagerState.currentPage)
                    GetStartedButton(onNextButtonClicked)
                }
        }

    }

    @ExperimentalPagerApi
    @Composable
    fun rememberPagerState(
        @androidx.annotation.IntRange(from = 0) initialPage: Int = 0
    ): PagerState = rememberSaveable(saver = PagerState.Saver) {
        PagerState(
            currentPage = initialPage
        )
    }

@Composable
@Preview(showBackground = true)
fun previewOnboarding(){
    OnBoardingScreen {
    }
}