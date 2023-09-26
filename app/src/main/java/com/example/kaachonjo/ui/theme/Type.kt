package com.example.kaachonjo.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.kaachonjo.R

val amaticFontFamily = FontFamily(
    Font(R.font.amatic_sc_bold, FontWeight.ExtraBold),
    Font(R.font.amatic_sc_regular, FontWeight.Normal)
)
val luckiestGuyFamily = FontFamily(
    Font(R.font.luckiest_guy_regular, FontWeight.ExtraBold)
)
val caveatFontFamily = FontFamily(
    Font(R.font.caveat_variablefont_wght, FontWeight.Bold)
)
val robotoFontFamily = FontFamily(
    Font(R.font.roboto_bold, FontWeight.Bold)
)
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
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