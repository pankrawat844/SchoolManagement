package com.app.schoolmanagement.students.repositories

import com.app.schoolmanagement.students.network.MyApi
import com.app.schoolmanagement.students.network.SafeApiRequest

class StudentSignupRepository(myApi: MyApi):SafeApiRequest() {

    suspend fun getstudentLogin(school_id:String,class_name:String,section_name:String,roll_no:String,passoword:String)
    {

    }
}