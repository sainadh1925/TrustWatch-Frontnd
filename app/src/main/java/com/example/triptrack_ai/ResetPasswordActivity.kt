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

class ResetPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        val email = intent.getStringExtra("email") ?: ""
        
        val etNewPassword = findViewById<EditText>(R.id.etNewPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)
        val btnReset = findViewById<Button>(R.id.btnResetPassword)

        btnReset.setOnClickListener {
            val password = etNewPassword.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()
            
            if (password.isEmpty() || confirmPassword.isEmpty()) {
                 Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                 Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else {
                resetPassword(email, password)
            }
        }
    }

    private fun resetPassword(email: String, newPass: String) {
        
        ApiClient.instance.resetPassword(email, newPass).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful && response.body() != null) {
                    val result = response.body()!!
                    Toast.makeText(this@ResetPasswordActivity, result, Toast.LENGTH_LONG).show()
                    if (result.contains("success")) {
                        // Go back to Login
                        val intent = Intent(this@ResetPasswordActivity, LoginActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                        finish()
                    }
                } else {
                    Toast.makeText(this@ResetPasswordActivity, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(this@ResetPasswordActivity, "Network Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
