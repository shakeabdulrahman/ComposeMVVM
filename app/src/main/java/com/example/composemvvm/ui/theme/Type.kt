package com.example.composemvvm.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.composemvvm.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val circularStdFamily = FontFamily(

    Font(R.font.circular_std, weight = FontWeight.W400),
    Font(R.font.circular_std, weight = FontWeight.W500),
    Font(R.font.circular_std, weight = FontWeight.W700),
)

val circularStdBold = FontFamily(
    Font(R.font.circular_std_bold, weight = FontWeight.W700)
)

val somaticFamily = FontFamily(
    Font(R.font.somatic, weight = FontWeight.W400),
)

val desyrel = FontFamily(Font(R.font.desyrel, weight = FontWeight.W500))

val billion_dreams = FontFamily(Font(R.font.billion_dreams, weight = FontWeight.W500))