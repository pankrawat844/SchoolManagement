package com.app.schoolmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.app.schoolmanagement.students.auth.LoginActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handler=Handler()
        handler.postAtTime(object :Runnable{
            override fun run() {
                Intent(this@SplashActivity,LoginActivity::class.java).apply {
                    startActivity(this)
                }
            }

        },3000)
    }
}
