package com.app.schoolmanagement.admin.ui.staff

interface StaffActivityListener {
    fun onStarted()
    fun onSuccess(msg: String)
    fun onError(msg: String)
}