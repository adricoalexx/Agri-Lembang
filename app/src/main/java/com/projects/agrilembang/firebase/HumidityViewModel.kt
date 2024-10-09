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

class HumidityViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val database = FirebaseDatabase.getInstance().reference.child("Sensor")

    private val _sensorHumid = MutableLiveData<List<Float>>()
    val sensorHumid : LiveData<List<Float>> get() = _sensorHumid

    init {
        fetchHumidityData()
    }

    private fun fetchHumidityData(){
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val humidityList = mutableListOf<Float>() // Change variable to `humidityList`
                for (sensorSnapshot in snapshot.children) {
                    val sensor = sensorSnapshot.getValue(Sensor::class.java)

                    if (sensor != null && sensor.humid != null && sensor.humid.isNotEmpty()) {
                        sensor.humid.replace("'","").toFloatOrNull()?.let {
                            humidityList.add(it) // Use `humidityList`
                        } ?: run {
                            Log.e("SensorData", "Invalid humidity format for sensor: $sensor")
                        }
                        Log.e("SensorData", "Sensor is null or humidity is missing: $sensor")
                    }
                }
                Log.d("SensorData", "Updated humidity: $humidityList") // Log for humidity

                if (humidityList.size == 6 && humidityList.distinct().size == humidityList.size) {
                    _sensorHumid.value = humidityList // Use `humidityList`
                } else {
                    Log.e("SensorData", "Unexpected number of humidity values: ${humidityList.size}")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Error : ", "onCancelled: ${error.message}")
            }

        })
    }
}
