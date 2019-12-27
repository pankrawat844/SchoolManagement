package com.app.schoolmanagement.admin.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.app.schoolmanagement.R
import com.app.schoolmanagement.admin.AdminActivity
import com.app.schoolmanagement.admin.network.response.AdminlLoginResponse
import com.app.schoolmanagement.databinding.ActivityAdminLoginBinding
import com.app.schoolmanagement.utils.hide
import com.app.schoolmanagement.utils.show
import com.app.schoolmanagement.utils.toast
import kotlinx.android.synthetic.main.activity_admin_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class AdminLoginActivity : AppCompatActivity(),AdminLoginListener,KodeinAware {
    override val kodein by kodein()
    lateinit var viewModel: AdminLoginViewModel
    lateinit var sharedPref: SharedPreferences

    val factory:AdminLoginViewModelFactory by instance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = getSharedPreferences("app", Context.MODE_PRIVATE)
        val databind=DataBindingUtil.setContentView<ActivityAdminLoginBinding>(this,R.layout.activity_admin_login)
        viewModel=ViewModelProviders.of(this,factory).get(AdminLoginViewModel::class.java)
        databind.data=viewModel
        viewModel.adminLoginListener=this

    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onFailure(msg: String) {
        toast(msg)
        progress_bar.hide()
    }

    override fun onSuccess(result: AdminlLoginResponse.Response?) {
        progress_bar.hide()
        toast("Login Successfully.")
        sharedPref.edit().also {
            it.putBoolean("islogin", true)
            it.putString("role", "student")
            it.putString("name", result?.username)
            it.putString("password", result?.password)
            it.commit()
        }
        Intent(this, AdminActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK

            finish()
            startActivity(it)
        }
    }

}
