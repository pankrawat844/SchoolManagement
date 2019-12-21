package com.app.schoolmanagement.students.auth.schoollogin

import com.app.schoolmanagement.students.network.response.SchoolLoginResponse
import com.app.schoolmanagement.students.room.entity.School

interface SchoolLoginListener {
fun onStarted()
    fun onFailure(msg:String)
    fun onSuccess(result: School?)
}