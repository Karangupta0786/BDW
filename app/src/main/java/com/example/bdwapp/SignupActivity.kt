package com.example.bdwapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        findViewById<LinearLayout>(R.id.ll_to_login).setOnClickListener {
            startActivity(Intent(this@SignupActivity,LoginActivity::class.java))
        }


    }
}