package com.wisp.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.graphics.ColorUtils

private val WispTypography = Typography(
    titleLarge = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
    titleMedium = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold),
    bodyLarge = TextStyle(fontSize = 15.sp, lineHeight = 22.sp),
    bodyMedium = TextStyle(fontSize = 14.sp),
    bodySmall = TextStyle(fontSize = 12.sp),
    labelSmall = TextStyle(fontSize = 11.sp)
)

private val WispTypographyLarge = Typography(
    titleLarge = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold),
    titleMedium = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold),
    bodyLarge = TextStyle(fontSize = 17.sp, lineHeight = 24.sp),
    bodyMedium = TextStyle(fontSize = 16.sp),
    bodySmall = TextStyle(fontSize = 14.sp),
    labelSmall = TextStyle(fontSize = 13.sp)
)

private fun lightenColor(color: Color, fraction: Float = 0.3f): Color {
    val hsl = FloatArray(3)
    ColorUtils.colorToHSL(color.toArgb(), hsl)
    hsl[1] = (hsl[1] * 0.7f).coerceIn(0f, 1f)
    hsl[2] = (hsl[2] + (1f - hsl[2]) * fraction).coerceIn(0f, 1f)
    return Color(ColorUtils.HSLToColor(hsl))
}

@Composable
fun WispTheme(
    isDarkTheme: Boolean = true,
    accentColor: Color = Color(0xFFFF9800),
    isLargeText: Boolean = false,
    content: @Composable () -> Unit
) {
    val secondary = remember(accentColor) { lightenColor(accentColor) }

    val colorScheme = if (isDarkTheme) {
        darkColorScheme(
            primary = accentColor,
            onPrimary = Color.White,
            secondary = secondary,
            background = Color(0xFF131215),
            surface = Color(0xFF1F1E21),
            surfaceVariant = Color(0xFF2B2A2E),
            onBackground = Color(0xFFE0E0E0),
            onSurface = Color(0xFFE0E0E0),
            onSurfaceVariant = Color(0xFF9998A0),
            outline = Color(0xFF343338)
        )
    } else {
        lightColorScheme(
            primary = accentColor,
            onPrimary = Color.White,
            secondary = secondary,
            background = Color(0xFFECECEC),
            surface = Color(0xFFF5F5F5),
            surfaceVariant = Color(0xFFE0E0E0),
            onBackground = Color(0xFF1C1B1F),
            onSurface = Color(0xFF1C1B1F),
            onSurfaceVariant = Color(0xFF6B6B6B),
            outline = Color(0xFFCCCCCC)
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = if (isLargeText) WispTypographyLarge else WispTypography,
        content = content
    )
}
