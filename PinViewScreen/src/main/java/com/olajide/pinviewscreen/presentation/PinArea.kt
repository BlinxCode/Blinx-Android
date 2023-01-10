package com.olajide.pinviewscreen.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.olajide.pinviewscreen.ui.theme.BlinxAppTheme
import com.olajide.pinviewscreen.ui.theme.Typography

@Composable
fun pinArea(
    charLimit: Int,
    textStyle: TextStyle = TextStyle(),
    pin: MutableState<String>,
) {

    val viewModel = viewModel<PinViewModel>()

    val state = viewModel.state
    state.pinLimit = charLimit
    pin.value = state.pin

    viewModel.setDefaultDotState(charLimit)

    viewModel.resetData()

    Box {
        Column(
            modifier = Modifier
                .padding(start = 40.dp, end = 40.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Spacer(modifier = Modifier.size(20.dp))

            //Setting Up List of Dots for pin
            LazyPinRow(charLimit, viewModel)

            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Passcode",
                style = Typography.labelMedium,
                textAlign = TextAlign.Start
            )

            //Buttons
            PinButtons(textStyle, onActions = viewModel::onAction)
        }
    }
}

@Composable
fun LazyPinRow(charLimit: Int, viewModel: PinViewModel) {
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .clip(CircleShape)
            .fillMaxWidth()
            // .background(Color(0xFFEEEEEE))
            .padding(10.dp)
    ) {

        items(charLimit) { dots ->
            run {
                Dot(viewModel.returnDotPosition(dots))
            }
        }
    }
}

@Composable
fun Dot(isChecked: Boolean) {
    Box(
        modifier = Modifier
            .requiredSize(
                size = 16.dp,
            )
            .background(
                color = if (isChecked) {
                    systemColor()

                } else
                    if (isSystemInDarkTheme()) {
                        Gray
                    } else {
                        Gray
                    },
                shape = CircleShape,
            ),
    )
}

@Composable
fun systemColor() = if (isSystemInDarkTheme()) {
    White
} else {
    Black
}


@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BlinxAppTheme {
        //  pinArea(onProceedClicked = {}, charLimit =4, pin = MutableState<String>())
    }
}