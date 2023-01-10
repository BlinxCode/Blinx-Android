package com.android.blinxapp.authenitcation.ui.pin

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.blinxapp.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.android.blinxapp.ui.theme.Typography
import com.olajide.pinviewscreen.presentation.pinArea


@Composable
fun PinSetupScreen(onProceedClicked: () -> Unit, context: Context) {
    val pin = remember{ mutableStateOf("") }
    val count = remember{ mutableStateOf(1) }
    val  charLimit = 4
    val anyMutableList = remember{mutableListOf("")}
    LaunchedEffect(Unit ){
        anyMutableList.clear()
    }

        Column(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
            verticalArrangement = Arrangement.spacedBy(10.dp)) {

            BlinxHeading(stringResource(if (count.value <=1){
                R.string.create_passcode
            } else{
                R.string.confirm_passcode
            }),
                stringResource(if (count.value<=1){
                    R.string.create_subHeading_passcode
                } else{
                    R.string.confirm_subHeader_passcode
                }) )
            if (pin.value.length==4){
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

            pinArea(
                charLimit = charLimit,
                textStyle = Typography.titleSmall, pin)

        }
}

@Preview(showBackground = true)
@Composable
fun PinScreenPreview(){
   // PinSetupScreen(onProceedClicked = {} )
}