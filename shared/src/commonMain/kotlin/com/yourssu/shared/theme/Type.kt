package com.yourssu.shared.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.yourssu.shared.generated.resources.Res
import com.yourssu.shared.generated.resources.Roboto_Bold
import com.yourssu.shared.generated.resources.Roboto_Medium
import com.yourssu.shared.generated.resources.Roboto_Regular
import org.jetbrains.compose.resources.Font

@Composable
fun rememberAppTypography(): KalendarTypography {
    val bold = Font(Res.font.Roboto_Bold, weight = FontWeight.Bold)
    val regular = Font(Res.font.Roboto_Regular, weight = FontWeight.Normal)
    val medium = Font(Res.font.Roboto_Medium, weight = FontWeight.Medium)

    val robotoFamily = remember(bold, regular, medium) { FontFamily(bold, regular, medium) }

    return remember(robotoFamily) {
        KalendarTypography(
            displayLarge = createTextStyle(
                fontSize = 57.sp,
                lineHeight = 64.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Normal,
                letterSpacing = (-0.25).sp
            ),
            displayMedium = createTextStyle(
                fontSize = 45.sp,
                lineHeight = 52.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.sp
            )
        )
    }
}

@Immutable
data class KalendarTypography(
    val displayLarge: TextStyle,
    val displayMedium: TextStyle
)

private fun createTextStyle(
    fontSize: TextUnit,
    lineHeight: TextUnit,
    fontFamily: FontFamily = FontFamily.Default,
    fontWeight: FontWeight = FontWeight.Normal,
    letterSpacing: TextUnit = TextUnit.Unspecified,
): TextStyle = TextStyle(
    fontFamily = fontFamily,
    fontWeight = fontWeight,
    fontSize = fontSize,
    lineHeight = lineHeight,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    ),
    letterSpacing = letterSpacing,
)
