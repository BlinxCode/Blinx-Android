package com.olajide.pinviewscreen.presentation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.olajide.pinviewscreen.data.PinState
import com.olajide.pinviewscreen.ui.theme.BlinxAppTheme
import com.olajide.pinviewscreen.ui.theme.Typography

@Composable
fun PinArea(
    modifier: Modifier = Modifier,
    buttonSpace: Dp = 10.dp,
    charLimit: Int
):String{

    val viewModel = viewModel<PinViewModel>()

    val state = viewModel.state
    state.pinLimit = charLimit

    //Setting Default state of the Dots
    viewModel.setDefaultDotState(charLimit)


    Box(modifier = modifier){
        Column(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter),
        verticalArrangement = Arrangement.spacedBy(buttonSpace)) {

            PinTitleScreen()
            Log.d("LogPinTest", state.pin)
            //Setting Up List of Dots for pin
            LazyPinRow(charLimit, pinState =state, viewModel )

            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Passcode",
                style = Typography.body2,
                textAlign = TextAlign.Start)

            Spacer(modifier = Modifier.size(25.dp))

            //Buttons
            PinButtons(onActions =viewModel::onAction)
        }
    }
    return state.pin
}


@Composable
 fun LazyPinRow(charLimit: Int, pinState: PinState, viewModel: PinViewModel) {
    var pin =pinState.pin
    Log.d("LogInputPin1", pin)
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .clip(CircleShape)
            .fillMaxWidth()
            // .background(Color(0xFFEEEEEE))
            .padding(30.dp)
    ) {

        items(charLimit) { dots ->
            run {
                Dot(viewModel.setDefaultDotState(charLimit)[dots])
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
                color = if (isChecked) Black else Gray,
                shape = CircleShape,
            ),
    )
}


@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BlinxAppTheme {
        PinArea( charLimit =4)
    }
}