package com.app.schoolmanagement.students.auth.studentlogin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.app.schoolmanagement.R
import com.app.schoolmanagement.databinding.ActivityStudentLoginBinding
import com.app.schoolmanagement.students.home.HomeActivity
import com.app.schoolmanagement.students.network.response.Student
import com.app.schoolmanagement.utils.hide
import com.app.schoolmanagement.utils.show
import com.app.schoolmanagement.utils.toast
import kotlinx.android.synthetic.main.activity_student_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class StudentLoginActivity : AppCompatActivity(), StudentLoginListener, KodeinAware {
    override val kodein by kodein()
    lateinit var viewModel: StudentLoginViewModel
    lateinit var sharedPref :SharedPreferences
    val factory: StudentLoginViewModelFactory by instance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = getSharedPreferences("app", Context.MODE_PRIVATE)
        val databind: ActivityStudentLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_student_login)
        viewModel = ViewModelProviders.of(this, factory).get(StudentLoginViewModel::class.java)
        databind.data = viewModel
        viewModel.school_id = intent.getStringExtra("school_id")
        viewModel.studentLoginListener = this
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(student: Student.Response) {
        progress_bar.hide()

        sharedPref.edit().also {
           it.putBoolean("islogin", true)
           it.putString("name", student.name)
           it.putString("school_name", student.schoolName)
           it.putString("roll_no", student.rollNo)
           it.putString("gender", student.gender)
           it.putString("address", student.address)
            it.commit()
        }
        Intent(this, HomeActivity::class.java).also {
            finish()
            startActivity(it)
        }
        toast(student.name!!)
    }

    override fun onFailure(msg: String) {
        progress_bar.hide()
        toast(msg)
    }
}