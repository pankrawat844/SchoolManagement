package com.app.schoolmanagement.students.auth.studentlogin

import com.app.schoolmanagement.students.network.response.Classes
import com.app.schoolmanagement.students.network.response.Student

interface StudentLoginListener {
    fun onStarted()
    fun onSuccess(student: Student.Response)
    fun onFailure(msg: String)
    fun onClassSuccess(classes: Classes)

}