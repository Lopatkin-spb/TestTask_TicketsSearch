package com.example.testtask_ticketssearch.presentation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.example.testtask_ticketssearch.R


@Composable
fun TestTask_TicketsSearch_Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        // DarkColorPalette
        darkColors(
            primary = colorResource(R.color.blue),
            background = colorResource(R.color.black),
        )
    } else {
        // LightColorPalette
        lightColors(
            primary = colorResource(R.color.blue),
            background = colorResource(R.color.black),

            /* Other default colors to override
            background = Color.White,
            surface = Color.White,
            onPrimary = Color.White,
            onSecondary = Color.Black,
            onBackground = Color.Black,
            onSurface = Color.Black,
             */
        )
    }

    MaterialTheme(
        colors = colors,
        content = content
    )
}