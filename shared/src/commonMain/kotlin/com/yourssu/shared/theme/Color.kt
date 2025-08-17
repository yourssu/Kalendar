package com.yourssu.shared.theme

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
 *  [LightTheme]
 *  밝은 컬러를 정의합니다.
 */
data class LightTheme(
    // Primary
    val primary: Color = TonalPalettes.primary40,
    val onPrimary: Color = TonalPalettes.primary100,
    val primaryContainer: Color = TonalPalettes.primary90,
    val onPrimaryContainer: Color = Color(0xFF4F378A),
    val primaryFixed: Color = TonalPalettes.primary90,
    val primaryFixedDim: Color = TonalPalettes.primary80,
    val onPrimaryFixed: Color = TonalPalettes.primary10,
    val onPrimaryFixedVariant: Color = TonalPalettes.primary30,

    // Secondary
    val secondary: Color = TonalPalettes.secondary40,
    val onSecondary: Color = TonalPalettes.secondary100,
    val secondaryContainer: Color = TonalPalettes.secondary90,
    val onSecondaryContainer: Color = Color(0xFF4A4459),
    val secondaryFixed: Color = TonalPalettes.secondary90,
    val secondaryFixedDim: Color = TonalPalettes.secondary80,
    val onSecondaryFixed: Color = TonalPalettes.secondary10,
    val onSecondaryFixedVariant: Color = TonalPalettes.secondary30,

    // Tertiary
    val tertiary: Color = TonalPalettes.tertiary40,
    val onTertiary: Color = TonalPalettes.tertiary100,
    val tertiaryContainer: Color = TonalPalettes.tertiary90,
    val onTertiaryContainer: Color = TonalPalettes.tertiary30,
    val tertiaryFixed: Color = TonalPalettes.tertiary90,
    val tertiaryFixedDim: Color = TonalPalettes.tertiary80,
    val onTertiaryFixed: Color = TonalPalettes.tertiary10,
    val onTertiaryFixedVariant: Color = TonalPalettes.tertiary30,

    // Error
    val error: Color = TonalPalettes.error40,
    val onError: Color = TonalPalettes.error100,
    val errorContainer: Color = TonalPalettes.error90,
    val onErrorContainer: Color = Color(0xFF852221),

    // Surface
    val surfaceDim: Color = Color(0xFFDED8E1),
    val surface: Color = Color(0xFFFEF7FF),
    val surfaceBright: Color = Color(0xFFFEF7FF),
    val surfaceContainerLowest: Color = TonalPalettes.neutral100,
    val surfaceContainerLow: Color = Color(0xFFF7F2FA),
    val surfaceContainer: Color = Color(0xFFF3EDF7),
    val surfaceContainerHigh: Color = Color(0xFFECE6F0),
    val surfaceContainerHighest: Color = TonalPalettes.neutral90,
    val onSurface: Color = TonalPalettes.neutral10,
    val onSurfaceVariant: Color = TonalPalettes.neutralVariant30,
    val outline: Color = TonalPalettes.neutralVariant50,
    val outlineVariant: Color = TonalPalettes.neutralVariant80,
    val inverseSurface: Color = TonalPalettes.neutral20,
    val inverseOnSurface: Color = TonalPalettes.neutral95,
    val inversePrimary: Color = TonalPalettes.primary80,
    val scrim: Color = TonalPalettes.neutral0,
    val shadow: Color = TonalPalettes.neutral0
)
