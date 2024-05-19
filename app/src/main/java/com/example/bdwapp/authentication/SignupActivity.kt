package com.example.bdwapp.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.bdwapp.R
import com.example.bdwapp.databinding.ActivitySignupBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import com.google.firebase.auth.userProfileChangeRequest
import java.util.concurrent.TimeUnit


class SignupActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var verificationID: String
    private var phoneNumber:String? = null
    private lateinit var binding:ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVars()


        binding.llToLogin.setOnClickListener {
            startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
            finish()
        }

        findViewById<CardView>(R.id.card_submit).setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.emailSignup.text.toString()
            val password = binding.passwordSignup.text.toString()
            val repeatPassword = binding.repeatPassword.text.toString()
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()){
                Toast.makeText(this, "please fill every fields!", Toast.LENGTH_SHORT).show()
            }
            else if (password != repeatPassword){
                Toast.makeText(this, "Passwords are not same!", Toast.LENGTH_SHORT).show()
            }
            else{
                signup(name,email,password)
                startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
                finish()
            }
        }

    }

    private fun signup(name:String,email:String,password:String){
        val user = auth.currentUser
        val userProfileChangeRequest = userProfileChangeRequest {
            displayName = name
        }
        user?.updateProfile(userProfileChangeRequest)
            ?.addOnCompleteListener {
                if (it.isSuccessful){
                    Log.d("userProfileChangeRequest","Profile updated with name")
                }
                else{
                    Log.e("userProfileRequest",it.exception.toString())
                }
            }
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener { task->
                if (task.isSuccessful){
                    Log.d("signup",task.result.toString())
                }
                else{
                    Log.e("signup",task.exception.toString())
                }
            }
    }

    private fun callOTP(){
        Log.e("callOtp","fun called")
        // The test phone number and code should be whitelisted in the console.
//        phoneNumber = "+91"+findViewById<EditText>(R.id.et_number_signup).text.toString()
//        val smsCode = findViewById<EditText>(R.id.otp).text.toString()

        auth = FirebaseAuth.getInstance() //auth initialization
        val firebaseAuthSettings = auth.firebaseAuthSettings


        // Configure faking the auto-retrieval with the whitelisted numbers.
//        firebaseAuthSettings.setAutoRetrievedSmsCodeForPhoneNumber(phoneNumber, smsCode)

        val options = phoneNumber?.let {
            PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(it) // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(this) // Activity (for callback binding)
                .setCallbacks(object : OnVerificationStateChangedCallbacks(){
                    override fun onVerificationCompleted(p0: PhoneAuthCredential) {

                    }

                    override fun onVerificationFailed(p0: FirebaseException) {
                        Log.e("SignupActivity:","something went wrong!!")
                    }

                    override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                        super.onCodeSent(p0, p1)
                        verificationID = p0
                    }
                }) // OnVerificationStateChangedCallbacks
                .build()
        }

        if (options != null) {
            PhoneAuthProvider.verifyPhoneNumber(options)
        }
    }

    private fun initVars(){
        auth = FirebaseAuth.getInstance()
    }

}