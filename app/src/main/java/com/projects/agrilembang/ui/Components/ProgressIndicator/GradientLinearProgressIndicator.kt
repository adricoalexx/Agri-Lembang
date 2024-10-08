package com.projects.agrilembang.ui.Components.ProgressIndicator

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GradientLinearProgressIndicator (
    modifier: Modifier = Modifier,
    battery : Int
) {
    val batteryLevel = battery.coerceIn(0, 100)
    val brush = Brush.linearGradient(
        colors = listOf(
            Color(0xFFFF0000),
            Color(0xFFF3D94C),
            Color(0xFF2AC610)
        )
    )

    Canvas(
        modifier = modifier
    ) {
        val fullWidth = size.width
        val barwidth = fullWidth * (batteryLevel / 100f)
        val height = size.height

        drawRoundRect(
            color = Color.Gray.copy(alpha = 0.3f),
            size = androidx.compose.ui.geometry.Size(fullWidth, height),
            cornerRadius = CornerRadius(8.dp.toPx())
        )
        drawRoundRect(
            brush = brush,
            size = androidx.compose.ui.geometry.Size(barwidth, height),
            cornerRadius = CornerRadius(8.dp.toPx())
        )
    }
}