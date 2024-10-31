package com.projects.agrilembang.retrofit.data

import com.google.gson.annotations.SerializedName

data class SensorResponse(
    @SerializedName("data") val data: List<SensorData>
)
