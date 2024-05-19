package com.example.bdwapp

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class MyPhoneAuthCallbacks : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

    companion object{
        // Create an instance of MyPhoneAuthCallbacks
        private val myPhoneAuthCallbacks = MyPhoneAuthCallbacks()

        // Store the callbacks instance in a variable
        val callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks = myPhoneAuthCallbacks
    }


    override fun onVerificationCompleted(p0: PhoneAuthCredential) {
        Log.e("onVerificationCompleted", "verification succeed")
    }

    override fun onVerificationFailed(p0: FirebaseException) {
        Log.e("onVerificationFailed","Verification failed")
    }
}