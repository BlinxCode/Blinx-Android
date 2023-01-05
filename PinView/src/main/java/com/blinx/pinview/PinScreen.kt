package com.blinx.pinview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.blinx.pinview.ui.theme.BlinxAppTheme

class PinScreen1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlinxAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")

    Box(modifier = Modifier.fillMaxWidth()){
        var dotLnegth = 4
        for(i in 0..dotLnegth ){
            Dot(Color.Green)
        }
    }

}

// This can be replaced with any composable as per requirement.
@Composable
fun Dot(
    color: Color,
) {
    Box(
        modifier = Modifier
            .requiredSize(
                size = 16.dp,
            )
            .background(
                color = color,
                shape = CircleShape,
            ),
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BlinxAppTheme {
        Greeting("Android")
    }
}