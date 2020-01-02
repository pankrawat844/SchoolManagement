package com.app.schoolmanagement.students.home.ui.home

interface AdminHomeFragmentListener {
    fun onDataChanged(name: String)
    fun onError(msg: String)
    fun onStarted()
}