package com.app.schoolmanagement.admin.ui.home

interface AdminHomeFragmentListener {
    fun onDataChanged(name: String)
    fun onError(msg: String)
    fun onStarted()
}