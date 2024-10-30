package com.projects.agrilembang.ui.Components.Chart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Timestamp
import com.projects.agrilembang.ui.theme.intersemibold
import kotlinx.coroutines.flow.StateFlow

@Composable
fun SensorCardWithChart(
    sensorName: String,
    chartData: StateFlow<Map<String, List<Pair<Float, String>>>>,
    dataType: String,
    color: Color,
    gradient: Int
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(300.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = sensorName,
            fontSize = 17.sp,
            fontFamily = intersemibold
        )
        LineChartView(
            chartDataState = chartData,
            title = sensorName, // Pastikan title sesuai dengan nama sensor
            colors = color,
            gradient = gradient
        )
    }
}
