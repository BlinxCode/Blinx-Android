package com.android.blinxapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.android.blinxapp.R


//Set font family
val jakartaFont = FontFamily(
    Font(R.font.jakarta_bold, FontWeight.Bold),
    Font(R.font.jakarta_light, FontWeight.Light),
    Font(R.font.jakarta_medium, FontWeight.Medium),
    Font(R.font.jakarta_regular, FontWeight.Normal),

)
// Set of Material typography styles to start with
val Typography = Typography(
    labelMedium = TextStyle(
        fontFamily = jakartaFont,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily =  jakartaFont,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = jakartaFont,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        letterSpacing = 0.5.sp
    ),
    titleSmall = TextStyle(
        fontFamily = jakartaFont,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp,
        letterSpacing = 0.5.sp
    ),
    displayLarge = TextStyle(
        fontFamily = jakartaFont,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,
        letterSpacing = 0.5.sp
    )
)
