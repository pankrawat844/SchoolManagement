package com.app.schoolmanagement.students.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.schoolmanagement.students.network.MyApi
import com.app.schoolmanagement.students.network.SafeApiRequest
import com.app.schoolmanagement.students.network.response.SchoolLoginResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SchoolLoginRepository(val myApi: MyApi):SafeApiRequest() {

       suspend fun schoolLogin(school_name:String,pass:String):SchoolLoginResponse
        {
            return apiRequest { myApi.school_login(school_name,pass) }
        }
}