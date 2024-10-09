package com.projects.agrilembang.ui.Components.Heatmap

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.projects.agrilembang.R
import kotlin.math.pow
import kotlin.math.sqrt

@Composable
fun HeatmapLayout(
    context: Context,
    temperatures: List<Float>,
    positions : List<Offset>,
    sensorNames : List<String>
) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(Color(0xFFFFFFFF), RoundedCornerShape(20.dp)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.denahagri),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .pointerInput(Unit){
                    detectTapGestures { offset ->
                        val canvasSize = size.toSize()
                        val clickedIndex = getClickedTemperatureIndex(temperatures, offset, canvasSize)
                        if (clickedIndex != null) {
                            val clickedTemperature = temperatures[clickedIndex]
                            Toast.makeText(context, "Suhu: $clickedTemperature °C", Toast.LENGTH_SHORT).show()
                        } else {
                            Log.d("Heatmap", "Titik tidak terdeteksi di posisi: ${offset.x}, ${offset.y}")
                        }
                    }
                }
        ) {
            positions.forEachIndexed { index, position ->
                val temperature = temperatures.getOrNull(index) ?: 0f
                val sensorName = sensorNames.getOrNull(index) ?: "Sensor"
                val pointColor = if (temperature > 50) Color.Red else Color.Green

                drawCircle(
                    color = pointColor.copy(alpha = 0.3f),
                    radius = 90f,
                    center = position
                )

                drawCircle(
                    color = pointColor,
                    radius = 15f,
                    center = position
                )

                drawContext.canvas.nativeCanvas.apply {
                    val text = String.format("%.1f°C", temperature)
                    drawText(
                        text,
                        position.x,
                        position.y + 40f,
                        android.graphics.Paint().apply {
                            color = Color.White.toArgb()
                            textSize = 30f
                            textAlign = android.graphics.Paint.Align.CENTER
                        }
                    )
                    drawText(
                        sensorName,
                        position.x,
                        position.y - 40f,
                        android.graphics.Paint().apply {
                            color = Color.White.toArgb()
                            textSize = 30f
                            textAlign = android.graphics.Paint.Align.CENTER
                        }
                    )
                }
            }
        }
    }
}

private fun getClickedTemperatureIndex(
    temperatures : List<Float>,
    tapOffset: Offset,
    canvasSize : Size
): Int? {
    val maxTemp = 100f
    val minTemp = 0f
    val spaceBetweenPoints = canvasSize.width / (temperatures.size - 1)

    temperatures.forEachIndexed { index, temperature ->
        val x = index * spaceBetweenPoints
        val y = canvasSize.height - ((temperature - minTemp) / (maxTemp - minTemp) * canvasSize.height)

        val distance = sqrt((tapOffset.x - x).pow(2) + (tapOffset.y - y).pow(2))

        if (distance < 15f) {
            return index
        }
    }

    return null
}