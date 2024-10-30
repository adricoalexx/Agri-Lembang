package com.projects.agrilembang.ui.Components.Chart

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.projects.agrilembang.R
import kotlinx.coroutines.flow.StateFlow

@Composable
fun TemperatureChart(
    chartDataState: StateFlow<Map<String, List<Pair<Float, String>>>>
) {
    val chartData by chartDataState.collectAsState()
    val context = LocalContext.current

    Log.d("LineChartView", "Observed chartData: ${chartData.size} sensors")

    val dataSets = mutableListOf<LineDataSet>()
    val labels = mutableListOf<String>()

    var minValue = Float.MAX_VALUE
    var maxValue = Float.MIN_VALUE

    chartData.forEach { (sensorName, dataPoints) ->
        val entries = mutableListOf<Entry>()

        dataPoints.forEachIndexed { index, (value, label) ->
            entries.add(Entry(index.toFloat(), value))
            minValue = minOf(minValue, value)
            maxValue = maxOf(maxValue, value)
            if (index >= labels.size) { // Ensure unique labels are added sequentially
                labels.add(label)
            }
        }

        Log.d("LineChartView", "Adding dataset for sensor: $sensorName with ${entries.size} entries")

        val dataSet = LineDataSet(entries, sensorName).apply {
            color = Color(0xFF155B36).toArgb()
            valueTextColor = android.graphics.Color.BLACK
            lineWidth = 3f
            fillDrawable = ContextCompat.getDrawable(context, R.drawable.chart_gradient_2)
            mode = LineDataSet.Mode.CUBIC_BEZIER
            setDrawFilled(true)
            setDrawCircles(false)
        }
        dataSets.add(dataSet)
    }

    val lineData = LineData(dataSets as List<ILineDataSet>)

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
                    setLabelCount(5, true) // Show only the last 5 labels
                    axisMinimum = 0f
                    axisMaximum = 4f // Dynamically adjusted to show 5 data points (0-4)
                    setAvoidFirstLastClipping(true)
                    isGranularityEnabled = true
                    labelRotationAngle = 45f
                    valueFormatter = IndexAxisValueFormatter(labels)
                }
                axisLeft.apply {
                    setDrawGridLines(false)
                    axisMinimum = minValue - 5f
                    axisMaximum = maxValue + 5f
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
            Log.d("LineChartView", "Updating chart with ${chartData.size} data points")

            // Ensure the labels are correctly set
            chart.data = lineData
            chart.xAxis.valueFormatter = IndexAxisValueFormatter(labels)
            chart.xAxis.setLabelCount(5, true) // Ensure only 5 labels are visible
            chart.xAxis.axisMaximum = (labels.size - 1).coerceAtLeast(4).toFloat() // Keep only 5 latest points
            chart.xAxis.axisMinimum = (labels.size - 5).coerceAtLeast(0).toFloat() // Start showing from the last 5

            // Update Y-axis dynamically based on the new data
            chart.axisLeft.axisMinimum = minValue - 5f // Ensure padding for minimum value
            chart.axisLeft.axisMaximum = maxValue + 5f // Ensure padding for maximum value

            chart.data.notifyDataChanged()
            chart.notifyDataSetChanged()
            chart.invalidate()

            if (lineData.entryCount > 0) {
                chart.setVisibleXRangeMaximum(5f) // Ensure only the last 5 data points are visible
                chart.moveViewToX(lineData.entryCount.toFloat() - 5) // Move to show the last 5 data points
            }
        }
    )
}
