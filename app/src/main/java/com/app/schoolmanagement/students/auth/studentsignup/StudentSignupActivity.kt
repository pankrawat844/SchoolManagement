package com.app.schoolmanagement.students.auth.studentsignup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.schoolmanagement.R
import com.app.schoolmanagement.students.auth.studentlogin.StudentLoginActivity
import kotlinx.android.synthetic.main.activity_student_signup.*

class StudentSignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_signup)
        signin.setOnClickListener {
            Intent(this, StudentLoginActivity::class.java).also {
                it.putExtra("school_id", intent.getStringExtra("school_id"))
                startActivity(it)
            }
        }
    }
}
