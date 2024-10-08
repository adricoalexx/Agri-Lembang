package com.projects.agrilembang.ui.Components.Chart

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

@Composable
fun LineChartView(
    chartData : List<Float>,
    title : String,
    colors : Color,
    gradient : Int
) {
    val context = LocalContext.current
    val entries = chartData.mapIndexed { index , data ->
        Entry(index.toFloat(), data)
    }


    val stepLabels = ( 0 until 6).map { "$it" }

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
                    labelCount = stepLabels.size
                    granularity = 1f
                    axisMinimum = 0f
                    axisMaximum = stepLabels.size.toFloat() - 1
                    valueFormatter = IndexAxisValueFormatter(stepLabels)
                }
                axisLeft.apply {
                    setDrawGridLines(false)
                    axisMinimum = 0f
                    axisMaximum = 90f
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

                description.isEnabled = false

                setTouchEnabled(true)
                setPinchZoom(true)
                invalidate()
            }
        },
        update = { chart ->
            chart.data = lineData
            chart.data.notifyDataChanged()
            chart.setVisibleXRangeMaximum(stepLabels.size.toFloat())
            chart.moveViewToX(entries.size.toFloat())
            chart.invalidate()

        }
    )
}