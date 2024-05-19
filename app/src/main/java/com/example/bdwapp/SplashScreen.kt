package com.example.bdwapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.bdwapp.authentication.SignupActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val intent = Intent(this@SplashScreen, SignupActivity::class.java)

        // TODO: Navigate directly to DASHBOARD If user is already logged in!

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            startActivity(intent)
            finish()
        },3000)

    }
}