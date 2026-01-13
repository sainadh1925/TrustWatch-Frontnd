package com.example.triptrack_ai

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SubscriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subscription)

        // Free -> Permissions directly
        findViewById<Button>(R.id.btnFree).setOnClickListener {
            startActivity(Intent(this, PermissionsActivity::class.java))
        }

        // Premium -> Payment first
        findViewById<Button>(R.id.btnPremium).setOnClickListener {
             startActivity(Intent(this, PaymentActivity::class.java))
        }
    }
}
