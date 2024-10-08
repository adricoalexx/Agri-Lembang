package com.projects.agrilembang.ui.Presentation.Menu.Suhu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.database.FirebaseDatabase
import com.projects.agrilembang.R
import com.projects.agrilembang.firebase.SensorViewModel
import com.projects.agrilembang.ui.Components.Chart.AverageLineChartView
import com.projects.agrilembang.ui.theme.intersemibold

@Composable
fun SuhuScreen(
) {

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
        SensorLayout()
        Spacer(modifier = Modifier.height(5.dp))
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(5.dp),
            border = CardDefaults.outlinedCardBorder(true),
            colors = CardDefaults.cardColors(Color.White),
            modifier = Modifier
                .size(350.dp, 310.dp)
        ){
            Column(
                verticalArrangement = Arrangement.spacedBy(7.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ){
                Text(
                    text = "Heatmap Suhu",
                    fontSize = 17.sp,
                    fontFamily = intersemibold
                )
                Text(
                    text = "Data diambil dari 6 sensor",
                    fontSize = 12.sp,
                    fontFamily = intersemibold,
                    color = Color.Gray
                )
                Image(
                    painter = painterResource(id = R.drawable.dummyheat),
                    contentDescription = "Dummy")
            }
        }
    }
}

