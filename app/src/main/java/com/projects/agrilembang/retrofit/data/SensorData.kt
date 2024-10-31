package com.projects.agrilembang.retrofit.data

import com.google.gson.annotations.SerializedName

data class SensorData(
    @SerializedName("id") val id: Int,
    @SerializedName("sensor_id") val sensorId: String,
    @SerializedName("temperature") val temperature: Double,
    @SerializedName("humidity") val humidity: Double,
    @SerializedName("battery") val battery: Int,
    @SerializedName("timestamp") val timestamp: String,
    @SerializedName("formatted_timestamp") val formattedTimestamp: String?
)
