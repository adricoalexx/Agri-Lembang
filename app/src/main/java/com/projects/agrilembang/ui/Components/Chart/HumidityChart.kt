package com.projects.agrilembang.ui.Components.Chart

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.projects.agrilembang.R

@Composable
fun HumidityChart(
    context: Context,
    humidities: List<Float>
) {
    AndroidView(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth(),
        factory = {
            LineChart(context).apply {
                updateChartData(humidities, context)
            }
        }
    )
}


private fun LineChart.updateChartData(
    humidities : List<Float>,
    context: Context
){
    val entries = humidities.mapIndexed { index , temp ->
        Entry(index.toFloat(), temp)
    }

    val dataSet = LineDataSet(entries, "Sensor Temperature").apply {
        color = Color(0xFF3354F4).toArgb()
        valueTextColor = android.graphics.Color.BLACK
        lineWidth = 3f
        mode = LineDataSet.Mode.CUBIC_BEZIER
        setDrawFilled(true)
        fillDrawable = ContextCompat.getDrawable(context, R.drawable.chart_gradient)
        setDrawCircles(false)
        xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            setDrawGridLines(false)
            granularity = 1f
        }
        axisLeft.apply {
            setDrawGridLines(false)
            granularity = 1f
        }
        axisRight.isEnabled = false
        description.isEnabled = false
        legend.isEnabled = false
        setDrawGridBackground(false)
        setBackgroundColor(android.graphics.Color.TRANSPARENT)
        xAxis.setDrawAxisLine(false)
        axisLeft.setDrawAxisLine(false)
        axisRight.setDrawAxisLine(false)

        xAxis.setDrawGridLines(false)
        axisLeft.setDrawGridLines(false)
        axisRight.setDrawGridLines(false)

        description.isEnabled = false

        setTouchEnabled(true)
        setPinchZoom(true)
    }
    val lineData = LineData(dataSet)

    this.data = lineData
    this.invalidate()
}
