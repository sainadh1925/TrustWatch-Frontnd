package com.example.triptrack_ai

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PermissionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissions)

        val btnEnable = findViewById<Button>(R.id.btnEnable)
        val switchSms = findViewById<Switch>(R.id.switchSms)
        val switchEmail = findViewById<Switch>(R.id.switchEmail)
        val switchNotif = findViewById<Switch>(R.id.switchNotifications)

        fun updateButtonState() {
            if (switchSms.isChecked || switchEmail.isChecked || switchNotif.isChecked) {
                btnEnable.text = "Go to Home"
                btnEnable.backgroundTintList = android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor("#2962FF")) // Blue
            } else {
                btnEnable.text = "Enable At Least One Permission"
                btnEnable.backgroundTintList = android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor("#B0BEC5")) // Grey
            }
        }

        val listener = android.widget.CompoundButton.OnCheckedChangeListener { _, _ -> updateButtonState() }
        switchSms.setOnCheckedChangeListener(listener)
        switchEmail.setOnCheckedChangeListener(listener)
        switchNotif.setOnCheckedChangeListener(listener)

        // Initial State
        updateButtonState()

        btnEnable.setOnClickListener {
            if (switchSms.isChecked || switchEmail.isChecked || switchNotif.isChecked) {
                // Navigate to Dashboard
                val intent = Intent(this, DashboardActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Please enable at least one permission", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
