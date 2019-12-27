package com.app.schoolmanagement.admin.auth

import com.app.schoolmanagement.admin.network.response.AdminlLoginResponse
import com.app.schoolmanagement.students.network.response.SchoolLoginResponse
import com.app.schoolmanagement.students.room.entity.School

interface AdminLoginListener {
fun onStarted()
    fun onFailure(msg:String)
    fun onSuccess(result: AdminlLoginResponse.Response?)
}