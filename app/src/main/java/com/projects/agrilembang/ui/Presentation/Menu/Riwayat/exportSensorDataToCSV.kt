package com.projects.agrilembang.ui.Presentation.Menu.Riwayat

import android.content.Context
import android.os.Environment
import android.widget.Toast
import com.projects.agrilembang.retrofit.data.SensorData
import java.io.File
import java.io.FileWriter
import java.io.IOException

fun exportSensorDataToCSV(context: Context, sensorDataList: List<SensorData>){
    val csvFileName = "SensorData.csv"
    val filePath = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)?.absolutePath
    val csvFile = File(filePath, csvFileName)

    try {
        FileWriter(csvFile).use { writer ->
            writer.append("Sensor ID,Sensor Name,Battery,Temperature,Humidity,Timestamp\n")

            sensorDataList.forEach { data ->

                writer.append("${data.id},${data.sensorId},${data.battery},${data.temperature},${data.humidity},${data.formattedTimestamp ?: data.timestamp}\n")

            }
        }
        Toast.makeText(context, "File Saved At $filePath/$csvFileName", Toast.LENGTH_LONG).show()
    } catch (e: IOException) {
        e.printStackTrace()
        Toast.makeText(context, "Failed to save file ${e.message}", Toast.LENGTH_SHORT).show()
    }
}