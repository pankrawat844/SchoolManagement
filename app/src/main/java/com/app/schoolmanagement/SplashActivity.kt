package com.app.schoolmanagement

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.app.schoolmanagement.students.auth.schoollogin.SchoolLoginActivity
import com.app.schoolmanagement.students.home.HomeActivity

class SplashActivity : AppCompatActivity() {
    val sharedPreferences = getSharedPreferences("app", Context.MODE_PRIVATE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handler=Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (sharedPreferences.getBoolean("islogin", false)) {
                    Intent(this@SplashActivity, HomeActivity::class.java).apply {
                        startActivity(this)
                        finish()
                    }
                } else {
                    Intent(this@SplashActivity, SchoolLoginActivity::class.java).apply {
                        startActivity(this)
                        finish()
                    }
                }
            }

        },3000)
    }
}
