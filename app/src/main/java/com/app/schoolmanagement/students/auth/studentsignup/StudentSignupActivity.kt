package com.app.schoolmanagement.students.auth.studentsignup

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.app.schoolmanagement.R
import com.app.schoolmanagement.databinding.ActivityStudentSignupBinding
import com.app.schoolmanagement.students.auth.studentlogin.StudentLoginActivity
import com.app.schoolmanagement.students.auth.studentlogin.StudentLoginListener
import com.app.schoolmanagement.students.home.HomeActivity
import com.app.schoolmanagement.students.network.response.Classes
import com.app.schoolmanagement.students.network.response.Student
import com.app.schoolmanagement.utils.hide
import com.app.schoolmanagement.utils.show
import com.app.schoolmanagement.utils.toast
import kotlinx.android.synthetic.main.activity_student_signup.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class StudentSignupActivity : AppCompatActivity(), KodeinAware, StudentLoginListener {
    override val kodein by kodein()
    var viewModel: StudentSignupViewModel? = null
    lateinit var sharedPref: SharedPreferences
    val factory: StudentSignupViewModelFactory by instance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = getSharedPreferences("app", Context.MODE_PRIVATE)
        val databind: ActivityStudentSignupBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_student_signup)
        viewModel = ViewModelProviders.of(this, factory).get(StudentSignupViewModel::class.java)
        viewModel?.school_id = intent.getStringExtra("school_id")
        databind.data = viewModel!!
        viewModel?.studentLoginListener = this

        signin.setOnClickListener {
            Intent(this, StudentLoginActivity::class.java).also {
                it.putExtra("school_id", intent.getStringExtra("school_id"))
                startActivity(it)
            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            viewModel?.getClasses()

        }
        class_name.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel?.getSection(parent?.getItemAtPosition(position).toString())!!
                }
            }

        }

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
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK

            finish()
            startActivity(it)
        }
    }

    override fun onFailure(msg: String) {
        progress_bar.hide()
        toast(msg)
    }

    override fun onClassSuccess(classes: Classes) {
        class_name.adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, classes.response!!.toItem())
    }

    override fun onSectionSuccess(classes: Classes) {
        section_name.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            classes.response!!.toSectinItem()
        )

    }

    private fun List<Classes.Data>.toItem(): List<String> {
        return this.map {
            it.className!!
        }
    }

    private fun List<Classes.Data>.toSectinItem(): List<String> {
        return this.map {
            it.sectionName!!
        }
    }
}
