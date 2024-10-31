package com.projects.agrilembang.ui.Presentation.Menu.Riwayat

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projects.agrilembang.retrofit.data.SensorData
import com.projects.agrilembang.ui.theme.intermedium
import com.projects.agrilembang.ui.theme.interregular
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun RiwayatDataList(sensorDataList: List<SensorData>) {

    val scrollState = rememberLazyListState()

    // Format waktu hanya untuk menampilkan jam
    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .border(1.dp, Color.Gray)) {

        // Header Tabel
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .border(1.dp, Color.Black)
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Timestamp",
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .border(1.dp, Color.Black),
                textAlign = TextAlign.Center,
                fontFamily = intermedium
            )
            Text(
                text = "Suhu",
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .border(1.dp, Color.Black),
                textAlign = TextAlign.Center,
                fontFamily = intermedium
            )
            Text(
                text = "Kelembaban",
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .border(1.dp, Color.Black),
                textAlign = TextAlign.Center,
                fontFamily = intermedium
            )
        }

        // Baris Data Tabel
        LazyColumn(state = scrollState, modifier = Modifier.fillMaxWidth()) {
            items(sensorDataList) { sensorData ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Gray),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Konversi timestamp menjadi hanya jam
                    val time = sensorData.timestamp?.let {
                        try {
                            timeFormat.format(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).parse(it))
                        } catch (e: Exception) {
                            "--:--" // Placeholder jika parsing gagal
                        }
                    } ?: "--:--"

                    Text(
                        text = time,
                        fontSize = 12.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 8.dp)
                            .border(0.5.dp, Color.Gray),
                        textAlign = TextAlign.Center,
                        fontFamily = interregular
                    )
                    Text(
                        text = "${sensorData.temperature}°C",
                        fontSize = 12.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 8.dp)
                            .border(0.5.dp, Color.Gray),
                        textAlign = TextAlign.Center,
                        fontFamily = interregular
                    )
                    Text(
                        text = "${sensorData.humidity}%",
                        fontSize = 12.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 8.dp)
                            .border(0.5.dp, Color.Gray),
                        textAlign = TextAlign.Center,
                        fontFamily = interregular
                    )
                }
            }
        }
    }
}