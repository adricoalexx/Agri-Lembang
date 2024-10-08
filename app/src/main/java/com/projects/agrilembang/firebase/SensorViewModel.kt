package com.projects.agrilembang.firebase

import android.util.Log
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.em
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.projects.agrilembang.firebase.data.Sensor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch

class SensorViewModel(
    private val savedStateHandle : SavedStateHandle
) : ViewModel() {

    private val database = FirebaseDatabase.getInstance().reference.child("Sensor")
    var sensorData = mutableStateOf<Map<String, Sensor>>(emptyMap())
        private set
    private val _sensorsData = MutableStateFlow<Map<String, List<Float>>>(emptyMap())
    val sensorsData : StateFlow<Map<String, List<Float>>> = _sensorsData

    private val _tempData = MutableStateFlow<List<Float>>(emptyList())
    val tempData: StateFlow<List<Float>> = _tempData

    private val _humidData = MutableStateFlow<List<Float>>(emptyList())
    val humidData: StateFlow<List<Float>> = _humidData


    init {
        fetchSensorData()
        savedStateHandle.set("sensorsData", _sensorsData.value)
    }

    private val maxSteps = 6


    fun updateData(sensorName : String, newData : Float) {
        viewModelScope.launch {
            val updatedSensors = _sensorsData.value.toMutableMap()
            val updatedData = updatedSensors[sensorName]?.toMutableList() ?: mutableListOf()
            Log.d("SensorViewModel", "New Data: $newData")

            if (updatedData.size >= maxSteps) {
                updatedData.removeAt(0)
            }
            updatedData.add(newData)

            updatedSensors[sensorName] = updatedData
            _sensorsData.value = updatedSensors
            Log.d("Data", "updateData: $updatedData")
            savedStateHandle.set("sensorsData", _sensorsData.value)

        }
    }

    fun updateSensorData(sensorName: String, newTempData : Float?, newHumidData : Float?) {
        viewModelScope.launch {
            newTempData?.let {
                val updatedTempData = _tempData.value.toMutableList()
                if (updatedTempData.size >= maxSteps) {
                    updatedTempData.removeAt(0)
                }
                updatedTempData.add(it)
                _tempData.value = updatedTempData
            }
            newHumidData?.let {
                val updatedHumidData = _humidData.value.toMutableList()
                if (updatedHumidData.size >= maxSteps) {
                    updatedHumidData.removeAt(0)
                }
                updatedHumidData.add(it)
                _humidData.value = updatedHumidData
            }
        }
    }
    private fun fetchSensorData() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val sensorMap = snapshot.children.associate { dataSnapshot ->
                    val key = dataSnapshot.key ?: ""
                    val sensor = dataSnapshot.getValue(Sensor::class.java) ?: Sensor()
                    key to  sensor
                }
                sensorData.value = sensorMap
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Error Database", "onCancelled: ${error.message}", )
            }
        })
    }

}