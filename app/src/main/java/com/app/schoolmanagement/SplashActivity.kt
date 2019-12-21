package com.app.schoolmanagement

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.app.schoolmanagement.students.auth.schoollogin.SchoolLoginActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handler=Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                Intent(this@SplashActivity, SchoolLoginActivity::class.java).apply {
                    startActivity(this)
                    finish()
                }
            }

        },3000)
    }
}
