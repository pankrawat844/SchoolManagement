package com.app.schoolmanagement.admin.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.schoolmanagement.admin.network.AdminApi
import com.app.schoolmanagement.admin.network.response.AdminlLoginResponse
import com.app.schoolmanagement.students.network.MyApi
import com.app.schoolmanagement.students.network.SafeApiRequest
import com.app.schoolmanagement.students.network.response.SchoolLoginResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminLoginRepository(val myApi: AdminApi):SafeApiRequest() {

       suspend fun adminLogin(school_name:String,pass:String):AdminlLoginResponse
        {
            return apiRequest { myApi.admin_login(school_name,pass) }
        }
}