package com.app.schoolmanagement.students.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.app.schoolmanagement.R
import com.app.schoolmanagement.databinding.ActivitySchoolLoginBinding
import com.app.schoolmanagement.utils.toast

class SchoolLoginActivity : AppCompatActivity(),SchoolLoginListener {
lateinit var viewModel: SchoolLoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivitySchoolLoginBinding=DataBindingUtil.setContentView(this,R.layout.activity_school_login)
        viewModel=ViewModelProviders.of(this).get(SchoolLoginViewModel::class.java)
        viewModel.schoolLoginListener=this
        binding.data=viewModel
        
    }

    override fun onStarted() {
    toast("Started")
    }

    override fun onFailure(msg: String) {
        toast(msg)
    }

    override fun onSuccess() {
        toast("success")
    }
}
