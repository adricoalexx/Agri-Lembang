package com.projects.agrilembang.retrofit

import com.projects.agrilembang.retrofit.data.SensorResponse
import retrofit2.http.GET

interface ApiService {
    @GET("api/all-sensor-data")
    suspend fun getAllSensorData(): SensorResponse
}