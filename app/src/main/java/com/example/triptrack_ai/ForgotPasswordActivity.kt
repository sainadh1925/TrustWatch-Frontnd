package com.example.triptrack_ai

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.triptrack_ai.api.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var btnSendOtp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        etEmail = findViewById(R.id.etEmail)
        btnSendOtp = findViewById(R.id.btnSendOtp)
        val tvBackToLogin = findViewById<TextView>(R.id.tvBackToLogin)

        // Initial State
        updateButtonState()

        val textWatcher = object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateButtonState()
            }
            override fun afterTextChanged(s: android.text.Editable?) {}
        }
        etEmail.addTextChangedListener(textWatcher)

        tvBackToLogin.setOnClickListener {
            finish()
        }

        btnSendOtp.setOnClickListener {
            val email = etEmail.text.toString().trim()
            if (email.isEmpty()) {
                Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show()
            } else {
                sendOtp(email)
            }
        }
    }

    private fun updateButtonState() {
        val email = etEmail.text.toString().trim()
        if (email.isNotEmpty()) {
            btnSendOtp.isEnabled = true
            btnSendOtp.alpha = 1.0f
        } else {
            btnSendOtp.isEnabled = false
            btnSendOtp.alpha = 0.5f
        }
    }

    private fun sendOtp(email: String) {
        
        ApiClient.instance.sendOtp(email).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful && response.body() != null) {
                    val result = response.body()!!
                    if (result.contains("OTP_SENT")) {
                        val intent = Intent(this@ForgotPasswordActivity, OtpVerifyActivity::class.java)
                        intent.putExtra("email", email)
                        startActivity(intent)
                    } else {
                         Toast.makeText(this@ForgotPasswordActivity, result, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@ForgotPasswordActivity, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(this@ForgotPasswordActivity, "Network Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
