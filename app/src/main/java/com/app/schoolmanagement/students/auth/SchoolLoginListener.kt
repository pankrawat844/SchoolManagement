package com.app.schoolmanagement.students.auth

import androidx.lifecycle.LiveData

interface SchoolLoginListener {
fun onStarted()
    fun onFailure(msg:String)
    fun onSuccess(result: LiveData<String>)
}