package com.app.schoolmanagement.students.auth

interface SchoolLoginListener {
fun onStarted()
    fun onFailure(msg:String)
    fun onSuccess()
}