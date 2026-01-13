package com.example.triptrack_ai

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.triptrack_ai.api.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import okhttp3.ResponseBody
import org.json.JSONObject

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ MUST MATCH XML FILE NAME
        setContentView(R.layout.activity_signup)

        // ✅ FIND VIEWS AFTER setContentView
        val etName = findViewById<EditText>(R.id.etFullName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)
        val btnSignup = findViewById<Button>(R.id.btnCreateAccount)
        val tvSignIn = findViewById<TextView>(R.id.tvSignIn)

        // Store the default color state list to restore it properly later
        val defaultColorStateList = etConfirmPassword.textColors

        // Initial Button State
        btnSignup.isEnabled = false
        btnSignup.alpha = 0.5f

        tvSignIn.setOnClickListener {
            finish()
        }

        val textWatcher = object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val name = etName.text.toString().trim()
                val email = etEmail.text.toString().trim()
                val password = etPassword.text.toString().trim()
                val confirmPassword = etConfirmPassword.text.toString().trim()

                // Check Password Match
                if (confirmPassword.isNotEmpty() && password != confirmPassword) {
                    etConfirmPassword.setTextColor(android.graphics.Color.RED)
                } else {
                    // Restore the original color state (handles dark/light mode etc)
                    etConfirmPassword.setTextColor(defaultColorStateList)
                }

                // Enable Button if all valid
                val isValid = name.isNotEmpty() && email.isNotEmpty() &&
                        password.isNotEmpty() && confirmPassword.isNotEmpty() &&
                        password == confirmPassword

                if (isValid) {
                    btnSignup.isEnabled = true
                    btnSignup.alpha = 1.0f
                } else {
                    btnSignup.isEnabled = false
                    btnSignup.alpha = 0.5f
                }
            }
            override fun afterTextChanged(s: android.text.Editable?) {}
        }

        etName.addTextChangedListener(textWatcher)
        etEmail.addTextChangedListener(textWatcher)
        etPassword.addTextChangedListener(textWatcher)
        etConfirmPassword.addTextChangedListener(textWatcher)

        btnSignup.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            signupUser(name, email, password)
        }
    }

    private fun signupUser(name: String, email: String, password: String) {
        
        ApiClient.instance.signup(name, email, password).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful && response.body() != null) {
                    var rawResponse = ""
                    try {
                        rawResponse = response.body()!!.string()
                        android.util.Log.d("API_DEBUG", "Raw Signup Response: $rawResponse")

                        // Manual Parsing with JSONObject - No Data Classes
                        val jsonObject = org.json.JSONObject(rawResponse)
                        val status = jsonObject.optString("status")
                        val message = jsonObject.optString("message")

                        if (status == "success") {
                            Toast.makeText(this@SignupActivity, "Signup Successful! Please Verify Email.", Toast.LENGTH_SHORT).show()
                            // Navigate to OTP Verification
                            val otpIntent = android.content.Intent(this@SignupActivity, OtpVerifyActivity::class.java)
                            val bundle = android.os.Bundle()
                            bundle.putString("email", email)
                            bundle.putString("flow", "signup")
                            otpIntent.putExtras(bundle)
                            startActivity(otpIntent)
                            finish()
                        } else {
                            Toast.makeText(this@SignupActivity, message, Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        // Show the actual server response if it's not JSON (useful for debugging HTML/PHP errors)
                        val preview = if (rawResponse.length > 100) rawResponse.substring(0, 100) + "..." else rawResponse
                        Toast.makeText(this@SignupActivity, "Server Response (Not JSON): $preview", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this@SignupActivity, "Server Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@SignupActivity, "Network Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
