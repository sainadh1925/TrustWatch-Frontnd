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
import okhttp3.ResponseBody
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var ivPasswordToggle: android.widget.ImageView
    private lateinit var cbRememberMe: android.widget.CheckBox
    private var isPasswordVisible = false
    private lateinit var sharedPreferences: android.content.SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnSignIn)
        ivPasswordToggle = findViewById(R.id.ivPasswordToggle)
        cbRememberMe = findViewById(R.id.cbRememberMe)
        val tvSignup = findViewById<TextView>(R.id.tvSignUp)
        val tvForgot = findViewById<TextView>(R.id.tvForgotPassword)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("TripTrackPrefs", MODE_PRIVATE)

        // Check if Remember Me was set
        if (sharedPreferences.getBoolean("isRemembered", false)) {
            etEmail.setText(sharedPreferences.getString("email", ""))
            etPassword.setText(sharedPreferences.getString("password", ""))
            cbRememberMe.isChecked = true
        }

        // Initial Button State (Disabled/Light)
        updateButtonState()

        // Text Watchers for Dynamic Button Color
        val textWatcher = object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateButtonState()
            }
            override fun afterTextChanged(s: android.text.Editable?) {}
        }
        etEmail.addTextChangedListener(textWatcher)
        etPassword.addTextChangedListener(textWatcher)

        // Password Visibility Toggle
        ivPasswordToggle.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            if (isPasswordVisible) {
                etPassword.transformationMethod = android.text.method.HideReturnsTransformationMethod.getInstance()
                ivPasswordToggle.alpha = 1.0f
                ivPasswordToggle.setColorFilter(getColor(R.color.primary)) 
            } else {
                etPassword.transformationMethod = android.text.method.PasswordTransformationMethod.getInstance()
                ivPasswordToggle.alpha = 0.5f
                ivPasswordToggle.setColorFilter(getColor(R.color.text_secondary))
            }
            // Move cursor to end
            etPassword.setSelection(etPassword.text.length)
        }

        // Go to Signup
        tvSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        // Go to Forgot Password
        tvForgot.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }

        // Login button
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            android.util.Log.d("AUTH_DEBUG", "Login Clicked -> Email: '$email', Password Length: ${password.length}")

            if (email == "admin" && password == "admin") {
                Toast.makeText(this, "Debug Mode: ByPassing Login", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, SubscriptionActivity::class.java))
                finish()
            } else if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Enter email and password", Toast.LENGTH_SHORT).show()
            } else {
                loginUser(email, password)
            }
        }
    }

    private fun updateButtonState() {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()
        
        if (email.isNotEmpty() && password.isNotEmpty()) {
            btnLogin.isEnabled = true
            btnLogin.alpha = 1.0f // Bright
        } else {
            btnLogin.isEnabled = false
            btnLogin.alpha = 0.5f // Light/Dimmed
        }
    }

    private fun loginUser(email: String, password: String) {
        
        android.util.Log.d("AUTH_DEBUG", "Sending Login Request to API...")

        ApiClient.instance.login(email, password).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                android.util.Log.d("AUTH_DEBUG", "API Response Code: ${response.code()}")
                
                if (response.isSuccessful && response.body() != null) {
                    var rawResponse = ""
                    try {
                        rawResponse = response.body()!!.string()
                        android.util.Log.d("API_DEBUG", "Raw Login Response: $rawResponse")

                        // Manual Parsing with JSONObject - No Data Classes
                        val jsonObject = org.json.JSONObject(rawResponse)
                        val status = jsonObject.optString("status")
                        val message = jsonObject.optString("message")

                        if (status == "success") {
                            // Save "Remember Me" preference
                            val editor = sharedPreferences.edit()
                            if (cbRememberMe.isChecked) {
                                editor.putBoolean("isRemembered", true)
                                editor.putString("email", email)
                                editor.putString("password", password)
                            } else {
                                editor.clear()
                            }
                            editor.apply()

                            // Navigate to Onboarding
                            val intent = Intent(this@LoginActivity, SubscriptionActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            android.util.Log.w("AUTH_DEBUG", "Login Failed: $message")
                            Toast.makeText(this@LoginActivity, message, Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        android.util.Log.e("AUTH_DEBUG", "JSON Parse Error", e)
                        val preview = if (rawResponse.length > 100) rawResponse.substring(0, 100) + "..." else rawResponse
                        Toast.makeText(this@LoginActivity, "JSON Parse Error: $preview", Toast.LENGTH_LONG).show()
                    }
                } else {
                     android.util.Log.e("AUTH_DEBUG", "Server Error: ${response.code()}")
                     Toast.makeText(this@LoginActivity, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                android.util.Log.e("AUTH_DEBUG", "Network Failure", t)
                Toast.makeText(this@LoginActivity, "Network Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
