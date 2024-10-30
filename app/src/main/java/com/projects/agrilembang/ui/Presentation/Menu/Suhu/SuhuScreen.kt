package com.projects.agrilembang.ui.Presentation.Menu.Suhu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.projects.agrilembang.R
import com.projects.agrilembang.firebase.HumidityViewModel
import com.projects.agrilembang.firebase.TemperatureViewModel
import com.projects.agrilembang.ui.Components.Chart.HumidityChart
import com.projects.agrilembang.ui.Components.Chart.SensorCardWithChart
import com.projects.agrilembang.ui.Components.Chart.TemperatureChart
import com.projects.agrilembang.ui.theme.intersemibold

@Composable
fun SuhuScreen(
    viewModel: TemperatureViewModel,
) {

    val sensorsData by viewModel.tempData.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp, start = 25.dp, end = 25.dp)
    ){
        Text(
            text = "Suhu",
            fontSize = 20.sp,
            fontFamily = intersemibold
        )
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(Color.White),
                elevation = CardDefaults.cardElevation(5.dp),
                border = CardDefaults.outlinedCardBorder(true),
                modifier = Modifier
                    .size(330.dp, 270.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Seluruh Sensor",
                        fontSize = 17.sp,
                        fontFamily = intersemibold,
                    )
                    Text(
                        text = "Data Realtime Per 3 jam",
                        fontSize = 12.sp,
                        fontFamily = intersemibold,
                        color = Color.Gray
                    )
                    TemperatureChart(viewModel.tempData)
                }
            }
        LazyRow (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ){
            items(sensorsData.keys.toList()) {
                val sensorData = sensorsData[it]?.map { it.first } ?: emptyList()
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(Color.White),
                    elevation = CardDefaults.cardElevation(5.dp),
                    border = CardDefaults.outlinedCardBorder(true),
                    modifier = Modifier
                        .size(330.dp, 270.dp)
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = "Data Realtime per 3 jam",
                            fontSize = 12.sp,
                            fontFamily = intersemibold,
                            color = Color.Gray
                        )
                        SensorCardWithChart(it, sensorData, "Temperature", Color(0xFF155B36), R.drawable.chart_gradient_2, )
                    }
                }
            }
        }
    }
}

