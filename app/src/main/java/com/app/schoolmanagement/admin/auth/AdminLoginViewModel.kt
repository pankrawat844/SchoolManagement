package com.app.schoolmanagement.admin.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.app.schoolmanagement.admin.repositories.AdminLoginRepository
import com.app.schoolmanagement.students.repositories.SchoolLoginRepository
import com.app.schoolmanagement.utils.ApiException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AdminLoginViewModel(val adminLoginRepository: AdminLoginRepository) : ViewModel()
{
    var user_name:String?=null
    var password:String?=null
    var adminLoginListener: AdminLoginListener?=null

    fun onLoginClick(view:View)
    {
        adminLoginListener?.onStarted()
        if(user_name.isNullOrEmpty() || password.isNullOrEmpty())
        {
            adminLoginListener?.onFailure("School Name Or Password Must Not Be Empty.")
            return
        }


        CoroutineScope(Dispatchers.Main).launch {
        try {
            val result = adminLoginRepository.adminLogin(user_name!!, password!!)
            result.response?.let {
                adminLoginListener?.onSuccess(it!!)
                return@launch
            }
            adminLoginListener?.onFailure(result?.message!!)
        }catch (e:ApiException) {
            adminLoginListener?.onFailure(e.message!!)
        }
        }

    }
}