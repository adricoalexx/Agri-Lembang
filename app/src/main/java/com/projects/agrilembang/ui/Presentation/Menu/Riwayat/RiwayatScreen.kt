package com.projects.agrilembang.ui.Presentation.Menu.Riwayat

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.projects.agrilembang.R
import com.projects.agrilembang.firebase.SensorViewModel
import com.projects.agrilembang.ui.theme.intersemibold
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun RiwayatScreen(
    viewModel : SensorViewModel = viewModel()
) {
    var currentDate by remember { mutableStateOf(getCurrentDate()) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp, start = 25.dp, end = 25.dp)
    ) {
        Text(
            text = "Riwayat",
            fontSize = 20.sp,
            fontFamily = intersemibold
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            IconButton(onClick = {
                currentDate = getPreviousDate(currentDate)
            }) {
                Image(
                    painter = painterResource(id = R.drawable.backbutton),
                    contentDescription = "Back Button",
                    modifier = Modifier
                        .size(25.dp)
                )
            }
            Text(
                text = currentDate,
                fontSize = 15.sp,
                fontFamily = intersemibold
            )
            IconButton(onClick = {
                currentDate = getNextDate(currentDate)
            }) {
                Image(
                    painter = painterResource(id = R.drawable.forwardicon),
                    contentDescription = "Back Button",
                    modifier = Modifier
                        .size(25.dp)
                )
            }
        }
    }
}


@SuppressLint("SimpleDateFormat")
fun getCurrentDate(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return dateFormat.format(Date())

}

@SuppressLint("SimpleDateFormat")
fun getPreviousDate(currentDate : String): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val calendar = Calendar.getInstance()

    calendar.time = dateFormat.parse(currentDate) ?: Date()
    calendar.add(Calendar.DAY_OF_YEAR, -1)

    return dateFormat.format(calendar.time)
}

@SuppressLint("SimpleDateFormat")
fun getNextDate(currentDate: String): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val calendar = Calendar.getInstance()

    calendar.time = dateFormat.parse(currentDate) ?: Date()
    calendar.add(Calendar.DAY_OF_YEAR, +1)

    return dateFormat.format(calendar.time)
}