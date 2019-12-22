package com.app.schoolmanagement.students.repositories

import com.app.schoolmanagement.students.network.MyApi
import com.app.schoolmanagement.students.network.SafeApiRequest
import com.app.schoolmanagement.students.network.response.Student

class StudentLoginRepository(val api: MyApi) : SafeApiRequest() {
    suspend fun getStudentDetails(school_id: String, roll_no: String, password: String): Student {
        return apiRequest { api.student_login(school_id, roll_no, password) }
    }
}