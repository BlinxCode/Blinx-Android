package com.android.blinxapp.dashboard.ui.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.android.blinxapp.ui.theme.primaryGreen
import com.android.blinxapp.ui.theme.white


@Composable
fun NewMessageAnimation() {

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp),
        shape = RoundedCornerShape(30.dp),

        modifier = Modifier

            .width(200.dp)
            .height(40.dp)
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(color = primaryGreen),
            horizontalArrangement = Arrangement.Center){

            Box(
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .clickable { }
                    .requiredSize(
                        size = 40.dp,
                    )
                    .background(
                        color = white,
                        shape = CircleShape,
                    ),
            ){

                MessageAnimation()
            }
            MarqueFadingText("Link multiple bank accounts for easy deposit",
                Modifier.align(alignment = Alignment.CenterVertically))
        }
    }
}


@Composable
fun MessageAnimation() {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.Url(
        "https://assets9.lottiefiles.com/packages/lf20_tvcrgegy.json"))

    LottieAnimation(
        modifier = Modifier
            .wrapContentSize()
            .size(70.dp)
            .wrapContentSize(Alignment.Center),
        composition =composition,
        iterations = LottieConstants.IterateForever,
        alignment = Alignment.Center
    )
}

