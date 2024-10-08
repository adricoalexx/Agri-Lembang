package com.projects.agrilembang.ui.Components.Image3D

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.projects.agrilembang.R

@Composable
fun HeatmapImage(
    sensorData: List<Pair<String, Float>>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.denahagri),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            alignment = Alignment.Center
        )
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val positions = listOf(
                Offset(0.2f * size.width, 0.2f * size.height),
                Offset(0.5f * size.width, 0.25f * size.height),
                Offset(0.8f * size.width, 0.2f * size.height),
                Offset(0.3f * size.width, 0.7f * size.height),
                Offset(0.6f * size.width, 0.6f * size.height),
                Offset(0.8f * size.width, 0.8f * size.height)
            )
            sensorData.forEachIndexed { index, (sensorName, temperature) ->
                if (index < positions.size) {
                    val colors = if (temperature > 30) Color.Red else Color.Blue
                    drawCircle(
                        color = colors,
                        radius = 20f,
                        center = positions[index]
                    )
                    drawContext.canvas.nativeCanvas.apply {
                        drawText(
                            sensorName,
                            positions[index].x,
                            positions[index].y - 35,
                            android.graphics.Paint().apply {
                                color = android.graphics.Color.BLACK
                                textAlign = android.graphics.Paint.Align.CENTER
                                textSize = 30f
                            }
                        )
                    }
                    drawContext.canvas.nativeCanvas.apply {
                        drawText(
                            "$temperature °C",
                            positions[index].x,
                            positions[index].y + 30,
                            android.graphics.Paint().apply {
                                color = android.graphics.Color.BLACK
                                textAlign = android.graphics.Paint.Align.CENTER
                                textSize = 30f
                            }
                        )
                    }
                }
            }
        }
    }
}

