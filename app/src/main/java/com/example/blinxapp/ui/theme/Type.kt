package com.example.blinxapp.ui.theme

import androidx.compose.material3.Typography
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
    bodyLarge = TextStyle(
        fontFamily = sfProFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
