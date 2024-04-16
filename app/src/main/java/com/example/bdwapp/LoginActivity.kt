package com.example.bdwapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<LinearLayout>(R.id.ll_to_signup).setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
        }

    }
}