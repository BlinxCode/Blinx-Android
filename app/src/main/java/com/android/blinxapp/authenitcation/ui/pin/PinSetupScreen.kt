package com.android.blinxapp.authenitcation.ui.pin

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.blinxapp.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

import com.android.blinxapp.ui.theme.Typography
import com.olajide.pinviewscreen.presentation.ComposablePinView


@Composable
fun PinSetupScreen(onProceedClicked: () -> Unit, context: Context) {
    val pin = remember{ mutableStateOf("") }
    val count = remember{ mutableStateOf(1) }
    val  charLimit = 6
    val anyMutableList = remember{mutableListOf("")}
    LaunchedEffect(Unit ){
        anyMutableList.clear()
    }

        Column(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)) {

            BlinxHeading((if (count.value <=1){
                stringResource( R.string.create_passcode)
            } else{
                stringResource( R.string.confirm_passcode)
            }),
                (if (count.value<=1){

                    stringResource( R.string.create_subHeading_passcode, charLimit)

                } else{
                    stringResource( R.string.confirm_subHeader_passcode, charLimit)

                }) )
            if (pin.value.length==charLimit){
                count.value ++
                Log.d("LogSizeX",count.value.toString())
                anyMutableList.add(pin.value)
               //
            }

            if (anyMutableList.size==2){
                if (anyMutableList.first().contentEquals(anyMutableList.last())){
                    LaunchedEffect(Unit) {
                        Toast.makeText(context, "Pin setup successfully", Toast.LENGTH_SHORT).show()
                        onProceedClicked()
                    }

                }else{
                    count.value =1
                    anyMutableList.clear()
                    pin.value = ""
                    Toast.makeText(context, "Pin setup failed", Toast.LENGTH_SHORT).show()
                }

            }

            ComposablePinView(
                charLimit = charLimit,
                text ="Passcode",
                textStyle = Typography.titleSmall, value= pin)

        }
}

@Preview(showBackground = true)
@Composable
fun PinScreenPreview(){
   // PinSetupScreen(onProceedClicked = {} )
}