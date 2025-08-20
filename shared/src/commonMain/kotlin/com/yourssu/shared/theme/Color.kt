package com.yourssu.shared.theme

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

/**
 * [TonalPalettes]
 *  기본적인 컬러를 정의합니다.
 */
private object TonalPalettes {
    // Primary
    val primary0: Color = Color(0xFF000000)
    val primary10: Color = Color(0xFF21005D)
    val primary20: Color = Color(0xFF381E72)
    val primary30: Color = Color(0xFF4F378B)
    val primary40: Color = Color(0xFF6750A4)
    val primary50: Color = Color(0xFF7F67BE)
    val primary60: Color = Color(0xFF9A82DB)
    val primary70: Color = Color(0xFFB69DF8)
    val primary80: Color = Color(0xFFD0BCFF)
    val primary90: Color = Color(0xFFEADDFF)
    val primary95: Color = Color(0xFFF6EDFF)
    val primary99: Color = Color(0xFFFFFBFE)
    val primary100: Color = Color(0xFFFFFFFF)

    // Secondary
    val secondary0: Color = Color(0xFF000000)
    val secondary10: Color = Color(0xFF1D192B)
    val secondary20: Color = Color(0xFF332D41)
    val secondary30: Color = Color(0xFF4A4458)
    val secondary40: Color = Color(0xFF625B71)
    val secondary50: Color = Color(0xFF7A7289)
    val secondary60: Color = Color(0xFF958DA5)
    val secondary70: Color = Color(0xFFB0A7C0)
    val secondary80: Color = Color(0xFFCCC2DC)
    val secondary90: Color = Color(0xFFE8DEF8)
    val secondary95: Color = Color(0xFFF6EDFF)
    val secondary99: Color = Color(0xFFFFFBFE)
    val secondary100: Color = Color(0xFFFFFFFF)

    // Tertiar
    val tertiary0: Color = Color(0xFF000000)
    val tertiary10: Color = Color(0xFF31111D)
    val tertiary20: Color = Color(0xFF492532)
    val tertiary30: Color = Color(0xFF633B48)
    val tertiary40: Color = Color(0xFF7D5260)
    val tertiary50: Color = Color(0xFF986977)
    val tertiary60: Color = Color(0xFFB58392)
    val tertiary70: Color = Color(0xFFD29DAC)
    val tertiary80: Color = Color(0xFFEFB8C8)
    val tertiary90: Color = Color(0xFFFFD8E4)
    val tertiary95: Color = Color(0xFFFFECF1)
    val tertiary99: Color = Color(0xFFFFFBFA)
    val tertiary100: Color = Color(0xFFFFFFFF)

    // Error
    val error0: Color = Color(0xFF000000)
    val error10: Color = Color(0xFF410E0B)
    val error20: Color = Color(0xFF601410)
    val error30: Color = Color(0xFF8C1D18)
    val error40: Color = Color(0xFFB3261E)
    val error50: Color = Color(0xFFDC362E)
    val error60: Color = Color(0xFFE46962)
    val error70: Color = Color(0xFFEC928E)
    val error80: Color = Color(0xFFF2B8B5)
    val error90: Color = Color(0xFFF9DEDC)
    val error95: Color = Color(0xFFFCEEEE)
    val error99: Color = Color(0xFFFFFBF9)
    val error100: Color = Color(0xFFFFFFFF)

    // Neutral
    val neutral0: Color = Color(0xFF000000)
    val neutral10: Color = Color(0xFF1D1B20)
    val neutral20: Color = Color(0xFF322F35)
    val neutral30: Color = Color(0xFF48464C)
    val neutral40: Color = Color(0xFF605D64)
    val neutral50: Color = Color(0xFF79767D)
    val neutral60: Color = Color(0xFF938F96)
    val neutral70: Color = Color(0xFFAEA9B1)
    val neutral80: Color = Color(0xFFCAC5CD)
    val neutral90: Color = Color(0xFFE6E0E9)
    val neutral95: Color = Color(0xFFF5EFF7)
    val neutral99: Color = Color(0xFFFFFBFF)
    val neutral100: Color = Color(0xFFFFFFFF)

    // Neutral Variant
    val neutralVariant0: Color = Color(0xFF000000)
    val neutralVariant10: Color = Color(0xFF1D1A22)
    val neutralVariant20: Color = Color(0xFF322F37)
    val neutralVariant30: Color = Color(0xFF49454F)
    val neutralVariant40: Color = Color(0xFF605D66)
    val neutralVariant50: Color = Color(0xFF79747E)
    val neutralVariant60: Color = Color(0xFF938F99)
    val neutralVariant70: Color = Color(0xFFAEA9B4)
    val neutralVariant80: Color = Color(0xFFCAC4D0)
    val neutralVariant90: Color = Color(0xFFE7E0EC)
    val neutralVariant95: Color = Color(0xFFF5EEFA)
    val neutralVariant99: Color = Color(0xFFFFFBFE)
    val neutralVariant100: Color = Color(0xFFFFFFFF)
}

/**
 *  [KalendarColors]
 *  Kalendar의 색상 기본 값을 정의합니다.
 */
@Stable
data class KalendarColors(
    // Primary
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val primaryFixed: Color,
    val primaryFixedDim: Color,
    val onPrimaryFixed: Color,
    val onPrimaryFixedVariant: Color,

    // Secondary
    val secondary: Color,
    val onSecondary: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,
    val secondaryFixed: Color,
    val secondaryFixedDim: Color,
    val onSecondaryFixed: Color,
    val onSecondaryFixedVariant: Color,

    // Tertiary
    val tertiary: Color,
    val onTertiary: Color,
    val tertiaryContainer: Color,
    val onTertiaryContainer: Color,
    val tertiaryFixed: Color,
    val tertiaryFixedDim: Color,
    val onTertiaryFixed: Color,
    val onTertiaryFixedVariant: Color,

    // Error
    val error: Color,
    val onError: Color,
    val errorContainer: Color,
    val onErrorContainer: Color,

    // Surface
    val surfaceDim: Color,
    val surface: Color,
    val surfaceBright: Color,
    val surfaceContainerLowest: Color,
    val surfaceContainerLow: Color,
    val surfaceContainer: Color,
    val surfaceContainerHigh: Color,
    val surfaceContainerHighest: Color,
    val onSurface: Color,
    val onSurfaceVariant: Color,
    val outline: Color,
    val outlineVariant: Color,
    val inverseSurface: Color,
    val inverseOnSurface: Color,
    val inversePrimary: Color,
    val scrim: Color,
    val shadow: Color
)

/**
 *  [LightKalendarColors]
 *  밝은 컬러를 정의합니다.
 */

val LightKalendarColors = KalendarColors(
    // Primary
    primary = TonalPalettes.primary40,
    onPrimary = TonalPalettes.primary100,
    primaryContainer = TonalPalettes.primary90,
    onPrimaryContainer = Color(0xFF4F378A),
    primaryFixed = TonalPalettes.primary90,
    primaryFixedDim = TonalPalettes.primary80,
    onPrimaryFixed = TonalPalettes.primary10,
    onPrimaryFixedVariant = TonalPalettes.primary30,

    // Secondary
    secondary = TonalPalettes.secondary40,
    onSecondary = TonalPalettes.secondary100,
    secondaryContainer = TonalPalettes.secondary90,
    onSecondaryContainer = Color(0xFF4A4459),
    secondaryFixed = TonalPalettes.secondary90,
    secondaryFixedDim = TonalPalettes.secondary80,
    onSecondaryFixed = TonalPalettes.secondary10,
    onSecondaryFixedVariant = TonalPalettes.secondary30,

    // Tertiary
    tertiary = TonalPalettes.tertiary40,
    onTertiary = TonalPalettes.tertiary100,
    tertiaryContainer = TonalPalettes.tertiary90,
    onTertiaryContainer = TonalPalettes.tertiary30,
    tertiaryFixed = TonalPalettes.tertiary90,
    tertiaryFixedDim = TonalPalettes.tertiary80,
    onTertiaryFixed = TonalPalettes.tertiary10,
    onTertiaryFixedVariant = TonalPalettes.tertiary30,

    // Error
    error = TonalPalettes.error40,
    onError = TonalPalettes.error100,
    errorContainer = TonalPalettes.error90,
    onErrorContainer = Color(0xFF852221),

    // Surface
    surfaceDim = Color(0xFFDED8E1),
    surface = Color(0xFFFEF7FF),
    surfaceBright = Color(0xFFFEF7FF),
    surfaceContainerLowest = TonalPalettes.neutral100,
    surfaceContainerLow = Color(0xFFF7F2FA),
    surfaceContainer = Color(0xFFF3EDF7),
    surfaceContainerHigh = Color(0xFFECE6F0),
    surfaceContainerHighest = TonalPalettes.neutral90,
    onSurface = TonalPalettes.neutral10,
    onSurfaceVariant = TonalPalettes.neutralVariant30,
    outline = TonalPalettes.neutralVariant50,
    outlineVariant = TonalPalettes.neutralVariant80,
    inverseSurface = TonalPalettes.neutral20,
    inverseOnSurface = TonalPalettes.neutral95,
    inversePrimary = TonalPalettes.primary80,
    scrim = TonalPalettes.neutral0,
    shadow = TonalPalettes.neutral0
)

/**
 *  [DarkKalendarColors]
 *  어두운 컬러를 정의합니다.
 */

val DarkKalendarColors = KalendarColors(
    // Primary
    primary = Color(0xFFD0BCFE),
    onPrimary = TonalPalettes.primary20,
    primaryContainer = TonalPalettes.primary30,
    onPrimaryContainer = TonalPalettes.primary90,
    primaryFixed = TonalPalettes.primary90,
    primaryFixedDim = TonalPalettes.primary80,
    onPrimaryFixed = TonalPalettes.primary10,
    onPrimaryFixedVariant = TonalPalettes.primary30,

    // Secondary
    secondary = TonalPalettes.secondary80,
    onSecondary = TonalPalettes.secondary20,
    secondaryContainer = TonalPalettes.secondary30,
    onSecondaryContainer = TonalPalettes.secondary90,
    secondaryFixed = TonalPalettes.secondary90,
    secondaryFixedDim = TonalPalettes.secondary80,
    onSecondaryFixed = TonalPalettes.secondary10,
    onSecondaryFixedVariant = TonalPalettes.secondary30,

    // Tertiary
    tertiary = TonalPalettes.tertiary80,
    onTertiary = TonalPalettes.tertiary20,
    tertiaryContainer = TonalPalettes.tertiary30,
    onTertiaryContainer = TonalPalettes.tertiary90,
    tertiaryFixed = TonalPalettes.tertiary90,
    tertiaryFixedDim = TonalPalettes.tertiary80,
    onTertiaryFixed = TonalPalettes.tertiary10,
    onTertiaryFixedVariant = TonalPalettes.tertiary30,

    // Error
    error = TonalPalettes.error80,
    onError = TonalPalettes.error20,
    errorContainer = TonalPalettes.error30,
    onErrorContainer = TonalPalettes.error90,

    // Surface
    surfaceDim = Color(0xFF141218),
    surface = Color(0xFF141218),
    surfaceBright = Color(0xFF3B383E),
    surfaceContainerLowest = Color(0xFF0F0D13),
    surfaceContainerLow = TonalPalettes.neutral10,
    surfaceContainer = Color(0xFF211F26),
    surfaceContainerHigh = Color(0xFF2B2930),
    surfaceContainerHighest = Color(0xFF36343B),
    onSurface = TonalPalettes.neutral90,
    onSurfaceVariant = TonalPalettes.neutralVariant80,
    outline = TonalPalettes.neutralVariant60,
    outlineVariant = TonalPalettes.neutralVariant30,
    inverseSurface = TonalPalettes.neutral90,
    inverseOnSurface = TonalPalettes.neutral20,
    inversePrimary = TonalPalettes.primary40,
    scrim = TonalPalettes.neutral0,
    shadow = TonalPalettes.neutral0
)
