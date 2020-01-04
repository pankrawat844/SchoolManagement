package com.app.schoolmanagement.admin.repositories

import com.app.schoolmanagement.admin.network.AdminApi
import com.app.schoolmanagement.admin.network.response.AddClassResponse
import com.app.schoolmanagement.admin.network.response.AdminlLoginResponse
import com.app.schoolmanagement.admin.network.response.Classes
import com.app.schoolmanagement.admin.network.response.StaffList
import com.app.schoolmanagement.students.network.SafeApiRequest
import retrofit2.Call

class AdminRepository(val myApi: AdminApi) : SafeApiRequest() {

    suspend fun adminLogin(school_name: String, pass: String): Call<AdminlLoginResponse> {
        return myApi.admin_login(school_name, pass)
    }

    fun addclass(
        school_id: String,
        class_name: String,
        section_name: String,
        total_student: String
    ): Call<AddClassResponse> {
        return myApi.add_class(school_id, class_name, section_name, total_student)
    }

    suspend fun getStaff(): Call<StaffList> {
        return myApi.getStaffList()
    }

    fun getClasses(
        school_id: String
    ): Call<Classes> {
        return myApi.get_classes(school_id)
    }

    suspend fun getSection(
        class_name: String
    ): Call<Classes> {
        return myApi.get_Section(class_name)
    }
}