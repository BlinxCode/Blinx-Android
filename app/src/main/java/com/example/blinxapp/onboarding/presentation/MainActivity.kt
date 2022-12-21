package com.example.blinxapp.onboarding.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.blinxapp.R
import com.example.blinxapp.onboarding.common.GetStartedButton
import com.example.blinxapp.onboarding.common.ImageWithBackground
import com.example.blinxapp.onboarding.common.OnboardingItems
import com.example.blinxapp.onboarding.common.PagerIndicator
import com.example.blinxapp.onboarding.data.model.OnBoardingData
import com.example.blinxapp.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalPagerApi::class)
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

                    ImageWithBackground(
                        backgroundDrawableResId = R.drawable.blinx_logo,
                        modifier = Modifier.fillMaxSize()
                    )


                    //Create a list of items to display in pager.
                    val items = ArrayList<OnBoardingData>()
                    items.add(
                        OnBoardingData(
                            R.drawable.blinx_background0,
                            "Create &",
                            "Fund a wallet of your choice"
                        )
                    )
                    items.add(
                        OnBoardingData(
                            R.drawable.blinx_background1,
                            "Pay bills",
                            "Automation your payments"
                        )
                    )

                    //Call rememberPageState Function
                    val pagerState = rememberPagerState(initialPage = 0)
                    OnBoardingPager(
                        item = items,
                        pagerState = pagerState
                    )
                }
            }
        }
    }

    @ExperimentalPagerApi
    @Composable
    fun OnBoardingPager(
        item: List<OnBoardingData>,
        pagerState: PagerState
    ) {
        Box {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                HorizontalPager(state = pagerState, count = 2) { page ->

                    // A function that house arrangement of views for the PageList to display
                    OnboardingItems(item, page)
                }
            }

            Box(modifier = Modifier.align(Alignment.BottomStart)) {
                Row(
                    modifier = Modifier
                        .padding(bottom = 40.dp, end = 20.dp, start = 20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    PagerIndicator(item.size, pagerState.currentPage)
                    GetStartedButton()
                }

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
}
