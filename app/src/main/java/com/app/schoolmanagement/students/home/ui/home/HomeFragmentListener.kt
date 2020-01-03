package com.app.schoolmanagement.students.home.ui.home
interface HomeFragmentListener {
    fun onDataChanged(name: String)
    fun onError(msg: String)
    fun onStarted()
}