package com.projects.agrilembang.ui.Components.Card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projects.agrilembang.firebase.data.Sensor
import com.projects.agrilembang.ui.Components.ProgressIndicator.GradientLinearProgressIndicator
import com.projects.agrilembang.ui.theme.interregular

@Composable
fun HomeCardLayout(
    sensor : Sensor
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(
                shape = RoundedCornerShape(5.dp)
                , color = Color.White),
    ){
        Text(
            text = "${sensor.device}",
            fontSize = 14.sp,
            fontFamily = interregular
        )
        Text(
            text = "${sensor.temp} °C",
            fontSize = 14.sp,
            fontFamily = interregular
        )
        Text(
            text = "${sensor.humid} %",
            fontSize = 14.sp,
            fontFamily = interregular
        )
        Column (
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Text(
                text = "${sensor.battery} %",
                fontSize = 14.sp,
                fontFamily = interregular
            )
            GradientLinearProgressIndicator(
                battery = sensor.battery?.toInt() ?: 0,
                modifier = Modifier
                    .size(70.dp, 10.dp)
            )
        }
    }
}
