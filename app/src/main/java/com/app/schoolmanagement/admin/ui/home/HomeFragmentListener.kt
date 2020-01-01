package com.app.schoolmanagement.admin.home.ui.home

interface HomeFragmentListener {
    fun onDataChanged(name: String)
    fun onError(msg: String)
}