package com.app.schoolmanagement.admin.ui.staff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.app.schoolmanagement.R
import com.app.schoolmanagement.databinding.ActivityStaffBinding

class StaffActivity : AppCompatActivity() {
        var viewModel:StaffViewModel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val databind:ActivityStaffBinding=DataBindingUtil.setContentView(this,R.layout.activity_staff)
        viewModel=ViewModelProviders.of(this).get(StaffViewModel::class.java)
        databind.viewmodel=viewModel

    }
}
