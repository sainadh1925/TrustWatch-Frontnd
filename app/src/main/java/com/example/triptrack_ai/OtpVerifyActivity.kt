package com.example.triptrack_ai

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.triptrack_ai.api.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OtpVerifyActivity : AppCompatActivity() {

    private lateinit var etOtp1: EditText
    private lateinit var etOtp2: EditText
    private lateinit var etOtp3: EditText
    private lateinit var etOtp4: EditText
    private lateinit var etOtp5: EditText
    private lateinit var etOtp6: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verify)

        val email = intent.getStringExtra("email") ?: "test@example.com"
        val flow = intent.getStringExtra("flow") ?: "forgot_password" // "signup" or "forgot_password"
        
        val tvDescription = findViewById<android.widget.TextView>(R.id.tvDescription)
        tvDescription.text = "Enter the 6-digit code sent to \n$email"

        etOtp1 = findViewById(R.id.otp1)
        etOtp2 = findViewById(R.id.otp2)
        etOtp3 = findViewById(R.id.otp3)
        etOtp4 = findViewById(R.id.otp4)
        etOtp5 = findViewById(R.id.otp5)
        etOtp6 = findViewById(R.id.otp6)

        setupOtpInputs()
        
        val btnVerify = findViewById<Button>(R.id.btnVerify)
        btnVerify.setOnClickListener {
            val otp = "${etOtp1.text}${etOtp2.text}${etOtp3.text}${etOtp4.text}${etOtp5.text}${etOtp6.text}"
            if (otp.length < 6) {
                Toast.makeText(this, "Please enter complete OTP", Toast.LENGTH_SHORT).show()
            } else {
                verifyOtp(email, otp, flow)
            }
        }
        
        val tvChangeEmail = findViewById<android.widget.TextView>(R.id.tvChangeEmail)
        tvChangeEmail.setOnClickListener {
            // Simply finish to go back to previous screen (Signup or Forgot Password)
            finish()
        }
    }

    private fun setupOtpInputs() {
        val edits = listOf(etOtp1, etOtp2, etOtp3, etOtp4, etOtp5, etOtp6)
        for (i in edits.indices) {
            edits[i].addTextChangedListener(object : android.text.TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.length == 1 && i < edits.size - 1) {
                        edits[i + 1].requestFocus()
                    }
                }
                override fun afterTextChanged(s: android.text.Editable?) {}
            })
            
            // Handle backspace to move focus back
            edits[i].setOnKeyListener { v, keyCode, event ->
                 if (keyCode == android.view.KeyEvent.KEYCODE_DEL && event.action == android.view.KeyEvent.ACTION_DOWN) {
                     if (edits[i].text.isEmpty() && i > 0) {
                         edits[i - 1].requestFocus()
                         return@setOnKeyListener true
                     }
                 }
                 false
            }
        }
    }

    private fun verifyOtp(email: String, otp: String, flow: String) {
        
        ApiClient.instance.verifyOtp(email, otp).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful && response.body() != null) {
                    val result = response.body()!!
                    if (result == "OTP_VERIFIED" || result.contains("success")) {
                        Toast.makeText(this@OtpVerifyActivity, "Verified Successfully!", Toast.LENGTH_SHORT).show()
                        
                        if (flow == "signup") {
                            // Go to App (Subscription/Home)
                            val intent = Intent(this@OtpVerifyActivity, SubscriptionActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                        } else {
                            // Go to Reset Password
                            val intent = Intent(this@OtpVerifyActivity, ResetPasswordActivity::class.java)
                            intent.putExtra("email", email)
                            startActivity(intent)
                        }
                        finish()
                    } else {
                        Toast.makeText(this@OtpVerifyActivity, result, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@OtpVerifyActivity, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(this@OtpVerifyActivity, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
