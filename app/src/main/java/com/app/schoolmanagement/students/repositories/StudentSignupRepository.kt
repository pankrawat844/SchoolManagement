package com.app.schoolmanagement.students.repositories

import androidx.lifecycle.MutableLiveData
import com.app.schoolmanagement.students.network.MyApi
import com.app.schoolmanagement.students.network.SafeApiRequest
import com.app.schoolmanagement.students.network.response.Classes
import com.app.schoolmanagement.students.network.response.Student
import retrofit2.Call

class StudentSignupRepository(val myApi: MyApi) : SafeApiRequest() {
    private val data = MutableLiveData<List<Classes.Data>>()
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

    fun getClasses(
        school_id: String
    ): Call<Classes>
    {
        return myApi.get_classes(school_id)
    }

    suspend fun getSection(
        class_name: String
    ): Call<Classes> {
        return myApi.get_Section(class_name)
    }
}