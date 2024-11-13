package com.projects.agrilembang.ui.Presentation.Menu.Beranda

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.projects.agrilembang.R
import com.projects.agrilembang.firebase.HeatmapViewModel
import com.projects.agrilembang.firebase.SensorViewModel
import com.projects.agrilembang.firebase.data.Sensor
import com.projects.agrilembang.navigation.Screen
import com.projects.agrilembang.ui.Components.Card.HomeCardLayout
import com.projects.agrilembang.ui.Components.Heatmap.HeatmapLayout
import com.projects.agrilembang.ui.theme.interbold
import com.projects.agrilembang.ui.theme.interregular
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun BerandaScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    sensorViewModel: SensorViewModel ,
    heatmapViewModel: HeatmapViewModel
) {
    val state = rememberLazyListState()
    val dateFormat = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("id", "ID"))
    val currentDate = dateFormat.format(Date())
    val database = FirebaseDatabase.getInstance().reference.child("Sensor")
    val sensors = sensorViewModel.sensorData.value
    val sensorTemp by heatmapViewModel.sensorTemp.observeAsState(emptyList())
    val sensorNames by heatmapViewModel.sensorNames.observeAsState(emptyList())
    val positions = listOf(
        Offset(20f, 220f),
        Offset(230f, 150f),
        Offset(250f, 300f),
        Offset(430f, 200f),
        Offset(500f, 400f),
        Offset(850f, 280f)
    )

    LaunchedEffect(Unit) {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.mapNotNull { it.getValue(Sensor::class.java) }
                    .filter { sensor -> sensor.device != null }
                    .forEach { sensor ->
                    val tempValue = sensor.temp?.toFloatOrNull()
                    val humidValue = sensor.humid?.toFloatOrNull()
                        if (tempValue != null && humidValue != null) {
                            sensorViewModel.updateSensorData(sensor.device.toString(), tempValue, humidValue)
                            Log.d("Rata Rata", "onDataChange: $tempValue, $humidValue")
                        }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Rata Rata Error", "onCancelled: $error", )
            }
        })
    }
    Image(
        painter = painterResource(
            id = R.drawable.backgrnd),
        contentDescription = "Background"
    )
    Column(
        modifier
            .fillMaxSize()
            .padding(top = 50.dp, start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.berandalogo),
                    contentDescription = "Beranda Logo",
                    modifier
                        .size(50.dp)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(3.dp),
                ) {
                    Text(
                        text = "Lembang Agri",
                        fontFamily = interbold,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                    Text(
                        text = currentDate,
                        fontSize = 10.sp,
                        fontFamily = interregular,
                        color = Color.White
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.notification),
                    contentDescription = "Notification",
                    modifier
                        .size(20.dp, 23.dp)
                )
                IconButton(
                    onClick = {
                        navController.navigate(Screen.Profile.route) {
                            popUpTo(Screen.Beranda.route) {
                                inclusive = true
                            }
                        }
                    }) {
                    Image(
                        painter = painterResource(
                            id = R.drawable.profileicon
                        ),
                        contentDescription = "User Picture",
                        modifier
                            .size(40.dp)
                    )
                }
            }
        }
        Spacer(
            modifier = modifier.height(10.dp)
        )
        if (sensorTemp.isNotEmpty()) {
            HeatmapLayout(
                context = LocalContext.current,
                temperatures = sensorTemp,
                positions = positions,
                sensorNames = sensorNames
            )
        } else {
            Text(text = "Loading chart data...")
        }
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp, end = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(sensors.values.toList()) { sensor ->
                HomeCardLayout(sensor)
            }
        }

    }
}




