package com.projects.agrilembang.retrofit

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projects.agrilembang.retrofit.data.SensorData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RetrofitViewModel : ViewModel() {

    var sensorDataList = mutableStateOf<List<SensorData>>(emptyList())
        private set

    var isLoading = mutableStateOf(false)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

    private val _filteredList = MutableStateFlow<List<SensorData>>(emptyList())
    val filteredList: StateFlow<List<SensorData>> = _filteredList

    init {
        fetchSensorData()
    }

    fun updateFilteredList(filteredList: List<SensorData>) {
        _filteredList.value = filteredList
    }

    private fun fetchSensorData(){
        viewModelScope.launch {
            try {
                isLoading.value = true
                errorMessage.value = null
                val response = ApiClient.apiService.getAllSensorData()
                sensorDataList.value = response.data
            } catch (e:Exception) {
                errorMessage.value = "Failed to fetch sensor data ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
    }
}