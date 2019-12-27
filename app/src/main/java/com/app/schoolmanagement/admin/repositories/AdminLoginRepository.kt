package com.app.schoolmanagement.admin.repositories

import com.app.schoolmanagement.admin.network.AdminApi
import com.app.schoolmanagement.admin.network.response.AdminlLoginResponse
import com.app.schoolmanagement.students.network.SafeApiRequest
import retrofit2.Call

class AdminLoginRepository(val myApi: AdminApi):SafeApiRequest() {

    suspend fun adminLogin(school_name: String, pass: String): Call<AdminlLoginResponse>
        {
            return myApi.admin_login(school_name, pass)
        }
}