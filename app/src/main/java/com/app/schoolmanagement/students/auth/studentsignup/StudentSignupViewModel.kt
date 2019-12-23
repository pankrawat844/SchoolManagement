package com.app.schoolmanagement.students.auth.studentsignup

import android.view.View
import androidx.lifecycle.ViewModel
import com.app.schoolmanagement.students.auth.studentlogin.StudentLoginListener
import com.app.schoolmanagement.students.repositories.StudentSignupRepository
import com.app.schoolmanagement.utils.ApiException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentSignupViewModel(val studentSignupRepository: StudentSignupRepository) : ViewModel() {
    var school_id: String? = null
    var class_name: String? = null
    var section_name: String? = null
    var roll_no: String? = null
    var password: String? = null
    var studentLoginListener: StudentLoginListener? = null

    fun onLoginSignup(view: View) {
        if (school_id.isNullOrEmpty()) {
            studentLoginListener?.onFailure("School Id Not Empty.")
            return
        }

        if (class_name.isNullOrEmpty()) {
            studentLoginListener?.onFailure("Please enter class name.")
            return
        }

        if (section_name.isNullOrEmpty()) {
            studentLoginListener?.onFailure("Please enter section name.")
            return
        }
        if (roll_no.isNullOrEmpty()) {
            studentLoginListener?.onFailure("Please enter roll no.")
            return
        }
        if (password.isNullOrEmpty()) {
            studentLoginListener?.onFailure("Please enter password.")
            return
        }

        CoroutineScope(Dispatchers.Main).launch {
            try {


                val response = studentSignupRepository.getstudentLogin(
                    school_id!!,
                    class_name!!,
                    section_name!!,
                    roll_no!!,
                    password!!
                )
                response.response.let {
                    studentLoginListener?.onSuccess(it!!)
                    return@launch
                }
                studentLoginListener?.onFailure(response.message!!)
            } catch (e: ApiException) {
                studentLoginListener?.onFailure(e.message!!)
            }

        }
    }

}