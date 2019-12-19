package com.app.schoolmanagement.students.auth

import android.view.View
import androidx.lifecycle.ViewModel

class SchoolLoginViewModel :ViewModel()
{
    var school_name:String?=null
    var password:String?=null
    var schoolLoginListener:SchoolLoginListener?=null

    fun onLoginClick(view:View)
    {
        schoolLoginListener?.onStarted()
        if(school_name.isNullOrEmpty() || password.isNullOrEmpty())
        {
            schoolLoginListener?.onFailure("School Name Or Password Must Not Be Empty.")
            return
        }
        schoolLoginListener?.onSuccess()
    }
}