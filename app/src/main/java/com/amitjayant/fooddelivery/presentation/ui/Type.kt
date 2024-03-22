package com.amitjayant.fooddelivery.presentation.ui

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.amitjayant.fooddelivery.R

// Font family
private val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)
private val font = GoogleFont("Nunito")
val nunito = FontFamily(
    Font(googleFont = font, fontProvider = provider, weight = FontWeight.Bold),
    Font(googleFont = font, fontProvider = provider, weight = FontWeight.SemiBold),
    Font(googleFont = font, fontProvider = provider, weight = FontWeight.Medium),
    Font(googleFont = font, fontProvider = provider, weight = FontWeight.Normal),
    Font(googleFont = font, fontProvider = provider, weight = FontWeight.Light)
)

// Set of Material typography styles
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = nunito,
        fontSize = 57.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 64.sp,
    ),
    displayMedium = TextStyle(
        fontFamily = nunito,
        fontSize = 45.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 52.sp,
    ),
    displaySmall = TextStyle(
        fontFamily = nunito,
        fontSize = 36.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 44.sp,
    ),
    headlineLarge = TextStyle(
        fontFamily = nunito,
        fontSize = 32.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 40.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = nunito,
        fontSize = 28.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 36.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = nunito,
        fontSize = 24.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 32.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = nunito,
        fontSize = 22.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 28.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = nunito,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 24.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = nunito,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 20.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = nunito,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = nunito,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = nunito,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 16.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = nunito,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 20.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = nunito,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 16.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = nunito,
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 16.sp,
    )
)