package com.projects.agrilembang.ui.Components.Chart

import androidx.compose.animation.core.animate
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.projects.agrilembang.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow

@Composable
fun LineChartView(
    chartDataState: StateFlow<Map<String, List<Pair<Float, String>>>>,
    title: String,
    colors: Color,
    gradient: Int
) {
    // Mengamati data dari StateFlow
    val chartData by chartDataState.collectAsState()
    val context = LocalContext.current

    // Memastikan bahwa title sebagai key memiliki data
    val singleSensorData = chartData[title] ?: emptyList()
    val entries = singleSensorData.mapIndexed { index, (value, _) ->
        Entry(index.toFloat(), value)
    }

    // Menggunakan timestamp dari data sebagai label X-axis
    val stepLabels = singleSensorData.map { it.second }

    // Buat LineDataSet hanya jika data tersedia
    val dataSet = LineDataSet(entries, title).apply {
        color = colors.toArgb()
        valueTextColor = android.graphics.Color.BLACK
        lineWidth = 3f
        mode = LineDataSet.Mode.CUBIC_BEZIER
        setDrawFilled(true)
        fillDrawable = ContextCompat.getDrawable(context, gradient)
        setDrawCircles(false)
    }

    val lineData = LineData(dataSet)

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        factory = { context ->
            LineChart(context).apply {
                xAxis.apply {
                    position = XAxis.XAxisPosition.BOTTOM
                    setDrawGridLines(false)
                    granularity = 1f
                    setLabelCount(5, true) // Hanya tampilkan 5 label terakhir
                    axisMinimum = (stepLabels.size - 5).coerceAtLeast(0).toFloat()
                    axisMaximum = (stepLabels.size - 1).coerceAtLeast(4).toFloat()
                    valueFormatter = IndexAxisValueFormatter(stepLabels)
                    labelRotationAngle = 45f
                }
                axisLeft.apply {
                    setDrawGridLines(false)
                    axisMinimum = singleSensorData.minOfOrNull { it.first }?.minus(5f) ?: 0f
                    axisMaximum = singleSensorData.maxOfOrNull { it.first }?.plus(5f) ?: 90f
                    granularity = 1f
                }
                axisRight.isEnabled = false
                description.isEnabled = false
                legend.isEnabled = false
                data = lineData

                setDrawGridBackground(false)
                setBackgroundColor(android.graphics.Color.TRANSPARENT)

                xAxis.setDrawAxisLine(false)
                axisLeft.setDrawAxisLine(false)
                axisRight.setDrawAxisLine(false)

                xAxis.setDrawGridLines(false)
                axisLeft.setDrawGridLines(false)
                axisRight.setDrawGridLines(false)

                setTouchEnabled(true)
                setPinchZoom(true)


                invalidate()
            }
        },
        update = { chart ->
            chart.data = lineData
            chart.xAxis.valueFormatter = IndexAxisValueFormatter(stepLabels)
            chart.xAxis.setLabelCount(5, true)
            chart.xAxis.axisMaximum = (stepLabels.size - 1).coerceAtLeast(4).toFloat()
            chart.xAxis.axisMinimum = (stepLabels.size - 5).coerceAtLeast(0).toFloat()

            chart.axisLeft.axisMinimum = singleSensorData.minOfOrNull { it.first }?.minus(5f) ?: 0f
            chart.axisLeft.axisMaximum = singleSensorData.maxOfOrNull { it.first }?.plus(5f) ?: 90f

            chart.data.notifyDataChanged()
            chart.notifyDataSetChanged()

            chart.invalidate()

            if (lineData.entryCount > 0) {
                chart.setVisibleXRangeMaximum(5f)
                chart.moveViewToX(lineData.entryCount.toFloat() - 5)
            }
        }
    )
}
