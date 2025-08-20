package com.yourssu.shared.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
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
fun rememberTypography(): KalendarTypography {
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
            ),
            displaySmall = createTextStyle(
                fontSize = 36.sp,
                lineHeight = 44.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.sp,
            ),
            headlineLarge = createTextStyle(
                fontSize = 32.sp,
                lineHeight = 40.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.sp,
            ),
            headlineMedium = createTextStyle(
                fontSize = 28.sp,
                lineHeight = 36.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.sp
            ),
            headlineSmall = createTextStyle(
                fontSize = 24.sp,
                lineHeight = 32.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.sp
            ),
            titleLarge = createTextStyle(
                fontSize = 22.sp,
                lineHeight = 28.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.sp,
            ),
            titleMedium = createTextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.15.sp
            ),
            titleSmall = createTextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.1.sp
            ),
            labelLarge = createTextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.1.sp
            ),
            labelMedium = createTextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.5.sp
            ),
            labelSmall = createTextStyle(
                fontSize = 11.sp,
                lineHeight = 16.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.5.sp
            ),
            bodyLarge = createTextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.5.sp
            ),
            bodyMedium = createTextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.25.sp
            ),
            bodySmall = createTextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.4.sp
            ),
            displayLargeEmphasized = createTextStyle(
                fontSize = 57.sp,
                lineHeight = 64.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Medium,
                letterSpacing = (-2.5).sp
            ),
            displayMediumEmphasized = createTextStyle(
                fontSize = 45.sp,
                lineHeight = 52.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.sp
            ),
            displaySmallEmphasized = createTextStyle(
                fontSize = 36.sp,
                lineHeight = 44.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.sp
            ),
            headlineLargeEmphasized = createTextStyle(
                fontSize = 32.sp,
                lineHeight = 40.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.sp
            ),
            headlineMediumEmphasized = createTextStyle(
                fontSize = 28.sp,
                lineHeight = 36.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.sp
            ),
            headlineSmallEmphasized = createTextStyle(
                fontSize = 24.sp,
                lineHeight = 32.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.sp
            ),
            titleLargeEmphasized = createTextStyle(
                fontSize = 22.sp,
                lineHeight = 28.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.sp
            ),
            titleMediumEmphasized = createTextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.15.sp
            ),
            titleSmallEmphasized = createTextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.1.sp
            ),
            labelLargeEmphasized = createTextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.1.sp
            ),
            labelMediumEmphasized = createTextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            ),
            labelSmallEmphasized = createTextStyle(
                fontSize = 11.sp,
                lineHeight = 16.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            ),
            bodyLargeEmphasized = createTextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.5.sp
            ),
            bodyMediumEmphasized = createTextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.25.sp
            ),
            bodySmallEmphasized = createTextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.4.sp
            )
        )
    }
}

@Stable
data class KalendarTypography(
    // Baseline
    val displayLarge: TextStyle,
    val displayMedium: TextStyle,
    val displaySmall: TextStyle,
    val headlineLarge: TextStyle,
    val headlineMedium: TextStyle,
    val headlineSmall: TextStyle,
    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val titleSmall: TextStyle,
    val labelLarge: TextStyle,
    val labelMedium: TextStyle,
    val labelSmall: TextStyle,
    val bodyLarge: TextStyle,
    val bodyMedium: TextStyle,
    val bodySmall: TextStyle,

    // Emphasis
    val displayLargeEmphasized: TextStyle,
    val displayMediumEmphasized: TextStyle,
    val displaySmallEmphasized: TextStyle,
    val headlineLargeEmphasized: TextStyle,
    val headlineMediumEmphasized: TextStyle,
    val headlineSmallEmphasized: TextStyle,
    val titleLargeEmphasized: TextStyle,
    val titleMediumEmphasized: TextStyle,
    val titleSmallEmphasized: TextStyle,
    val labelLargeEmphasized: TextStyle,
    val labelMediumEmphasized: TextStyle,
    val labelSmallEmphasized: TextStyle,
    val bodyLargeEmphasized: TextStyle,
    val bodyMediumEmphasized: TextStyle,
    val bodySmallEmphasized: TextStyle
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
