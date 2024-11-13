package com.projects.agrilembang.ui.Presentation.Menu.Riwayat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projects.agrilembang.R
import com.projects.agrilembang.retrofit.RetrofitViewModel
import com.projects.agrilembang.ui.theme.intermedium
import com.projects.agrilembang.ui.theme.intersemibold
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun RiwayatScreen(
    viewModel: RetrofitViewModel
) {
    val sensorDataList by viewModel.sensorDataList
    var selectedSensorId by remember { mutableStateOf<String?>(null) }
    var expanded by remember { mutableStateOf(false) }

    val displayDateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
    var selectedDate by remember { mutableStateOf(Date()) }

    val filteredList by remember(selectedSensorId, selectedDate, sensorDataList) {
        derivedStateOf {
            val filteredBySensorId = if (selectedSensorId != null) {
                sensorDataList.filter { it.sensorId == selectedSensorId }
            } else {
                sensorDataList
            }

            filteredBySensorId.filter { data ->
                val dataDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(data.timestamp)
                dataDate != null && displayDateFormat.format(dataDate) == displayDateFormat.format(selectedDate)
            }
        }
    }

    LaunchedEffect(filteredList) {
        viewModel.updateFilteredList(filteredList)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(7.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp, start = 25.dp, end = 25.dp)
    ) {

        Text(
            text = "Riwayat Sensor",
            fontSize = 24.sp,
            fontFamily = intersemibold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = {
                val calendar = Calendar.getInstance()
                calendar.time = selectedDate
                calendar.add(Calendar.DATE, -1)
                selectedDate = calendar.time
            }) {
                Image(
                    painter = painterResource(id = R.drawable.backbutton),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }

            Text(text = displayDateFormat.format(selectedDate), fontSize = 20.sp, fontFamily = intersemibold)

            IconButton(onClick = {
                val calendar = Calendar.getInstance()
                calendar.time = selectedDate
                calendar.add(Calendar.DATE, 1)
                selectedDate = calendar.time
            }) {
                Image(
                    painter = painterResource(id = R.drawable.forwardicon),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(start = 20.dp, end = 20.dp)
                .background(Color(0xFFEEEEEE), RoundedCornerShape(20.dp))
                .clickable { expanded = true }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, end = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = selectedSensorId ?: "Select Sensor",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontFamily = intermedium
                )

                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown Arrow",
                    tint = Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                sensorDataList.map { it.sensorId }.distinct().forEach { sensorId ->
                    DropdownMenuItem(
                        onClick = {
                            selectedSensorId = sensorId
                            expanded = false
                        },
                        text = { Text(sensorId) }
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            when {
                viewModel.isLoading.value -> CircularProgressIndicator()
                viewModel.errorMessage.value != null -> Column {
                    Text(text = viewModel.errorMessage.value ?: "Unknown Error")
                    Button(onClick = { viewModel.retryFetchData() }) {
                        Text(text = "Retry")
                    }
                }
                filteredList.isEmpty() -> Text(text = "Data masih kosong", fontSize = 16.sp, color = Color.Gray)
                else -> RiwayatDataList(filteredList)
            }
        }
    }
}


