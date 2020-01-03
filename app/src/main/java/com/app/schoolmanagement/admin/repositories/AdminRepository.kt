package com.app.schoolmanagement.admin.repositories

import com.app.schoolmanagement.admin.network.AdminApi
import com.app.schoolmanagement.admin.network.response.AddClassResponse
import com.app.schoolmanagement.admin.network.response.AdminlLoginResponse
import com.app.schoolmanagement.admin.network.response.Staff
import com.app.schoolmanagement.students.network.SafeApiRequest
import retrofit2.Call

class AdminRepository(val myApi: AdminApi) : SafeApiRequest() {

    suspend fun adminLogin(school_name: String, pass: String): Call<AdminlLoginResponse>
        {
            return myApi.admin_login(school_name, pass)
        }

    suspend fun addclass(
        school_id: String,
        class_name: String,
        section_name: String,
        total_student: String
    ): Call<AddClassResponse>
    {
        return myApi.add_class(school_id, class_name, section_name, total_student)
    }

    suspend fun getStaff():Call<Staff>
    {
        return myApi.getStaffList()
    }
}