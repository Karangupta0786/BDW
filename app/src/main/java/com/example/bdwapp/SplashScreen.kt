package com.example.bdwapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.bdwapp.authentication.SignupActivity
import com.google.firebase.auth.FirebaseAuth

class SplashScreen : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    private lateinit var intent: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null){
            intent = Intent(this,MainActivity::class.java)
        }
        else{
            intent = Intent(this@SplashScreen, SignupActivity::class.java)
        }

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            startActivity(intent)
            finish()
        },3000)

    }
}