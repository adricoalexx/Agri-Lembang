package com.projects.agrilembang.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.projects.agrilembang.firebase.data.Sensor

class HeatmapViewModel : ViewModel() {

    private val database = FirebaseDatabase.getInstance().reference.child("Sensor")

    private val _sensorTemp = MutableLiveData<List<Float>>()
    val sensorTemp : LiveData<List<Float>> get() = _sensorTemp

    private val _sensorNames = MutableLiveData<List<String>>()
    val sensorNames: LiveData<List<String>> get() = _sensorNames

    init {
        fetchSensorData()
    }
    private fun fetchSensorData(){
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val sensorList = mutableListOf<Float>()
                val sensorNameList = mutableListOf<String>()

                for (sensorSnapshot in snapshot.children) {
                    val sensor = sensorSnapshot.getValue(Sensor::class.java)
                    Log.d("Heatmap", "Retrieved sensor: $sensor")
                    if (sensor != null && sensor.temp != null && sensor.temp.isNotEmpty()){
                        sensor.temp.replace("'", "").toFloatOrNull()?.let {
                            sensorList.add(it)
                        } ?: run {
                            Log.e("Heatmap", "Invalid temperature format for sensor: $sensor")
                        }
                        sensorNameList.add(sensor.device ?: "Unknown Sensor")
                    } else {
                        Log.e("Heatmap", "Sensor is null or temperature is missing: $sensor")
                    }
                }
                Log.d("Heatmap", "Updated temperatures: $sensorList")

                _sensorTemp.value = sensorList
                _sensorNames.value = sensorNameList
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Heatmap Error : ", "onCancelled: ${error.message}")
            }

        })
    }
}