package com.app.schoolmanagement.students.businfo

import com.app.schoolmanagement.students.network.response.Homework
import com.app.schoolmanagement.students.network.response.Timetable


interface BusInfoListener {
    fun onStarted()
    fun onImageSuccess(data: Homework)
    fun onPdfSuccess(data: Homework)
    fun onSuccess(data: Timetable)
    fun onFailure(msg: String)
}