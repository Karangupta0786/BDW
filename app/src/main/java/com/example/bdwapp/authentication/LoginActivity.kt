package com.example.bdwapp.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.bdwapp.MainActivity
import com.example.bdwapp.R
import com.example.bdwapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    private val binding:ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initVars()

        findViewById<LinearLayout>(R.id.ll_to_signup).setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
        }

        binding.cardLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            login(email, password)
        }


    }
    private fun initVars(){
        auth = FirebaseAuth.getInstance()
    }

    private fun login(email:String,password:String){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    Log.d("login",it.result.toString())
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                }
                else{
                    Log.e("login",it.exception.toString())
                }
            }
    }

}