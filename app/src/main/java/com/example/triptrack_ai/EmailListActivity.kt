package com.example.triptrack_ai

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class EmailListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_list)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        // Mock Item 1: Spam
        findViewById<LinearLayout>(R.id.item_email_1).setOnClickListener {
            val intent = Intent(this, ScanDetailActivity::class.java)
            intent.putExtra("IS_SAFE", false)
            intent.putExtra("CONTENT", "Subject: Action Required: Unauthorized access detected\nFrom: support@paypal-security-alert.com")
            intent.putExtra("REASON", "Spoofed Domain: The sender domain 'paypal-security-alert.com' is not verified and mimics a legitimate service. The email creates false urgency.")
            startActivity(intent)
        }

        // Mock Item 2: Safe
        findViewById<LinearLayout>(R.id.item_email_2).setOnClickListener {
            val intent = Intent(this, ScanDetailActivity::class.java)
            intent.putExtra("IS_SAFE", true)
            intent.putExtra("CONTENT", "Subject: The Future of AI: Top 10 trends to watch\nFrom: newsletter@techweekly.com")
            intent.putExtra("REASON", "Safe. Domain verified via DMARC/SPF. No malicious links or attachments found.")
            startActivity(intent)
        }
    }
}
