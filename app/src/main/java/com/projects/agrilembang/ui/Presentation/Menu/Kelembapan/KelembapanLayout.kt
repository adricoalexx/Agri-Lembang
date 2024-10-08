
package com.projects.agrilembang.ui.Presentation.Menu.Kelembapan

import android.provider.ContactsContract.Data
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.projects.agrilembang.R
import com.projects.agrilembang.firebase.SensorViewModel
import com.projects.agrilembang.firebase.data.Sensor
import com.projects.agrilembang.ui.Components.Chart.SensorCardWithChart
import com.projects.agrilembang.ui.theme.intersemibold

@Composable
fun KelembapanLayout(
    title : String,
    sensorViewModel: SensorViewModel = viewModel()
) {
    val sensorsData by sensorViewModel.sensorsData.collectAsState()
    val database = FirebaseDatabase.getInstance().reference.child("Sensor")


    LaunchedEffect(Unit) {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val sensors = snapshot.children.mapNotNull { it.getValue(Sensor::class.java) }
                sensors.forEach { sensor ->
                    sensor.humid?.toFloatOrNull()?.let { temperature ->
                        sensorViewModel.updateData(sensor.device.toString(), temperature)
                    }
                }
                Log.d("Temperature", "onDataChange: $sensors")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Temperature Error", "onCancelled: $error" )
            }

        })
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(sensorsData.keys.toList()) { sensorGroup ->
            val sensorData = sensorsData[sensorGroup] ?: emptyList()
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
                        text = title,
                        fontSize = 17.sp,
                        fontFamily = intersemibold
                    )
                    Text(
                        text = "Data Realtime Per 3 Jam",
                        fontSize = 12.sp,
                        fontFamily = intersemibold,
                        color = Color.Gray
                    )
                    SensorCardWithChart(
                        sensorGroup,
                        sensorData,
                        "Humidity",
                        Color(0xFF3354F4),
                        R.drawable.chart_gradient
                    )
                }
            }
        }
    }
}