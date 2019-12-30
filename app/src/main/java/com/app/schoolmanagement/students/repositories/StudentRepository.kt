package com.app.schoolmanagement.students.repositories

import com.app.schoolmanagement.students.network.MyApi
import com.app.schoolmanagement.students.network.SafeApiRequest
import com.app.schoolmanagement.students.network.response.Student
import okhttp3.ResponseBody
import retrofit2.Call

class StudentRepository(val api: MyApi) : SafeApiRequest() {
    suspend fun getStudentDetails(school_id: String, roll_no: String, password: String): Student {
        return apiRequest { api.student_login(school_id, roll_no, password) }
    }

    suspend fun edit_profile(
        name: String,
        mobile: String,
        password: String,
        student_id: String
    ): Call<ResponseBody> {
        return api.edit_profile(student_id, name, mobile, password)
    }
}