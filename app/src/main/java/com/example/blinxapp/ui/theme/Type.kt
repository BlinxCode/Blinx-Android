package com.example.blinxapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.blinxapp.R

//Set font family
val sfProFont = FontFamily(
    Font(R.font.sf_pro_rounded_bold, FontWeight.Bold),
    Font(R.font.sf_pro_rounded_light, FontWeight.Light),
    Font(R.font.sf_pro_rounded_medium, FontWeight.Medium),
    Font(R.font.sf_pro_rounded_regular, FontWeight.Normal),
    Font(R.font.sf_pro_rounded_semibold, FontWeight.SemiBold)

    )

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = sfProFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)