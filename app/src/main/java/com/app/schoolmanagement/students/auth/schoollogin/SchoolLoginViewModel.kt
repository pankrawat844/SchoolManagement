package com.app.schoolmanagement.students.auth.schoollogin

import android.view.View
import androidx.lifecycle.ViewModel
import com.app.schoolmanagement.students.repositories.SchoolLoginRepository
import com.app.schoolmanagement.utils.ApiException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SchoolLoginViewModel(val schoolLoginRepository: SchoolLoginRepository) : ViewModel()
{
    var school_name:String?=null
    var password:String?=null
    var schoolLoginListener: SchoolLoginListener?=null

    fun onLoginClick(view:View)
    {
        schoolLoginListener?.onStarted()
        if(school_name.isNullOrEmpty() || password.isNullOrEmpty())
        {
            schoolLoginListener?.onFailure("School Name Or Password Must Not Be Empty.")
            return
        }


        CoroutineScope(Dispatchers.Main).launch {
        try {
            val result = schoolLoginRepository.schoolLogin(school_name!!, password!!)
            result.response?.let {
                schoolLoginListener?.onSuccess(it)
                return@launch
            }
            schoolLoginListener?.onFailure(result?.message!!)
        }catch (e:ApiException) {
            schoolLoginListener?.onFailure(e.message!!)
        }
        }

    }
}