package com.projects.agrilembang.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.projects.agrilembang.firebase.data.Sensor

class TemperatureViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val database = FirebaseDatabase.getInstance().reference.child("Sensor")

    private val _sensorTemps = MutableLiveData<List<Float>>()
    val sensorTemps : LiveData<List<Float>> get() = _sensorTemps

    init {
        fetchTemperatureData()
    }

    private fun fetchTemperatureData() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val temperatureList = mutableListOf<Float>()
                for (sensorSnapshot in snapshot.children) {
                    val sensor = sensorSnapshot.getValue(Sensor::class.java)

                    if (sensor != null && sensor.temp != null && sensor.temp.isNotEmpty()) {
                        sensor.temp.replace("'", "").toFloatOrNull()?.let {
                            temperatureList.add(it)
                        } ?: run {
                            Log.e("SensorData", "Invalid temperature format for sensor: $sensor")
                        }
                        Log.e("SensorData", "Sensor is null or temperature is missing: $sensor")
                    }
                }
                Log.d("SensorData", "Updated temperatures: $temperatureList")

                if (temperatureList.size == 6) {
                    _sensorTemps.value = temperatureList
                } else {
                    Log.e(
                        "SensorData",
                        "Unexpected number of temperatures: ${temperatureList.size}"
                    )
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Error : ", "onCancelled: ${error.message}")
            }

        })
    }
}