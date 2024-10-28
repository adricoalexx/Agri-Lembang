package com.projects.agrilembang.firebase

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.projects.agrilembang.firebase.data.Sensor
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HumidityViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val database = FirebaseDatabase.getInstance().reference.child("Sensor")
    var sensorData = mutableStateOf<Map<String, Sensor>>(emptyMap())
        private set
    private val _humidData = MutableStateFlow<Map<String, List<Pair<Float, String>>>>(emptyMap())
    val humidData: StateFlow<Map<String, List<Pair<Float, String>>>> = _humidData

    private val maxSteps = 6
    private val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

    init {
        fetchSensorData()
        TempLoop()
    }

    private fun fetchSensorData() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val sensorMap = snapshot.children.associate { dataSnapshot ->
                    val key = dataSnapshot.key ?: ""
                    val sensor = dataSnapshot.getValue(Sensor::class.java) ?: Sensor()
                    key to sensor
                }
                sensorData.value = sensorMap
                Log.d("Fetch Database", "onDataChange: $sensorMap")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Error Database", "onCancelled: ${error.message}", )
            }

        })
    }

    private fun updateChartData() {
        viewModelScope.launch {
            val updateData = mutableMapOf<String, List<Pair<Float, String>>>()

            sensorData.value.forEach { (sensorName, sensor) ->
                sensor.humid?.toFloatOrNull()?.let { newData ->
                    val currentTime = dateFormat.format(Date())
                    val existingData =
                        _humidData.value[sensorName]?.toMutableList() ?: mutableListOf()

                    if (existingData.size >= maxSteps) {
                        existingData.removeAt(0)
                    }
                    existingData.add(Pair(newData, currentTime))
                    updateData[sensorName] = existingData
                }
            }
            if (updateData.isNotEmpty()) {
                _humidData.value = updateData
            }
        }
    }

    private fun TempLoop(){
        viewModelScope.launch {
            updateChartData()
            while (true) {
                delay(5000L)
                updateChartData()
            }
        }
    }
}
