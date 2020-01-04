package com.app.schoolmanagement.admin.ui.staff

import com.app.schoolmanagement.admin.network.response.Classes

interface StaffActivityListener {
    fun onStarted()
    fun onSuccess(msg: String)
    fun onError(msg: String)
    fun onClassSuccess(it: Classes)
    fun onSectionSuccess(it: Classes)
}