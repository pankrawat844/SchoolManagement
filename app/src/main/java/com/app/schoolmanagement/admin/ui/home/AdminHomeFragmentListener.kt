package com.app.schoolmanagement.admin.ui.home

import com.app.schoolmanagement.admin.network.response.Classes

interface AdminHomeFragmentListener {
    fun onDataChanged(name: String)
    fun onError(msg: String)
    fun onStarted()

}