package com.app.schoolmanagement.students.repositories

import com.app.schoolmanagement.students.network.MyApi
import com.app.schoolmanagement.students.network.SafeApiRequest
import com.app.schoolmanagement.students.network.response.Student

class StudentSignupRepository(val myApi: MyApi) : SafeApiRequest() {

    suspend fun getstudentLogin(
        school_id: String,
        class_name: String,
        section_name: String,
        roll_no: String,
        passoword: String
    ): Student
    {
        return apiRequest {
            myApi.student_signup(
                school_id,
                class_name,
                section_name,
                roll_no,
                passoword
            )
        }
    }
}