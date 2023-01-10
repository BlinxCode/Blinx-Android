package com.example.blinxapp.authenitcation.ui.onboarding.common

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.blinxapp.R
import com.example.blinxapp.common.systemColorInverse
import com.example.blinxapp.authenitcation.ui.onboarding.data.model.OnBoardingData
import com.example.blinxapp.ui.theme.*

@Composable
fun ImageWithBackground(
    @DrawableRes backgroundDrawableResId: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(backgroundDrawableResId),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .matchParentSize()
        )
    }
}


@Composable
fun OnboardingItems(
    item: List<OnBoardingData>,
    page: Int
) {
    Box {
        //Image modifier
        Image(
            painter = painterResource(id = item[page].image),
            contentDescription = item[page].title,
            modifier = Modifier
                .fillMaxWidth(),
            alignment = Alignment.Center
        )

    }
}

@Composable
fun PagerTexts(
    item: List<OnBoardingData>,
    page: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier =modifier
    ) {
        //Title modifier
        Text(
            text = item[page].title,
            modifier = Modifier.padding(end = 20.dp, start = 20.dp),
            color = secondaryGrey,
            style = Typography.displayLarge,
            textAlign = TextAlign.Start
        )

        //Description modifier
        Text(
            text = item[page].description,
            modifier = Modifier
                .padding(top = 10.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth(),
            color = systemColorInverse(),
            style = Typography.displayLarge,
            textAlign = TextAlign.Start

        )
    }
}

@Composable
fun PagerIndicator(size: Int, currentPage: Int) {
    Row(
        modifier = Modifier.padding(
            top = 25
                .dp
        )
    ) {
        repeat(size) {
            Indicator(isSelected = it == currentPage)
        }

    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(targetValue = if (isSelected) 60.dp else 6.dp)
    Box(
        modifier = Modifier
            .padding(5.dp)
            .height(6.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                if (isSelected) primaryGreen else secondaryGrey.copy(
                    alpha = 0.5f
                )
            )
    )
}

@Composable
fun GetStartedButton(onNextButtonClicked: () -> Unit) {
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = primaryGreen,
        contentColor = contentColorFor(backgroundColor = whiteBlinx)
    )
    Button(
        modifier = Modifier
            .height(60.dp)
            .width(60.dp),
        onClick = { onNextButtonClicked()},
        colors = buttonColors,
        shape = RoundedCornerShape(20)
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowForwardIos,
            contentDescription = stringResource(R.string.getStarted)

        )
    }
}

@Composable
fun BackButton() {
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = primaryGreen,
        contentColor = contentColorFor(backgroundColor = whiteBlinx)
    )
    Button(
        modifier = Modifier
            .height(60.dp)
            .width(60.dp),
        onClick = { },
        colors = buttonColors,
        shape = RoundedCornerShape(20)
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowForwardIos,
            contentDescription = stringResource(R.string.getStarted)

        )
    }
}
