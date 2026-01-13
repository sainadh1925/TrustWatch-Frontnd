package com.example.triptrack_ai

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class SmsListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms_list)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        // Mock Item 1: Spam
        findViewById<LinearLayout>(R.id.item_sms_1).setOnClickListener {
            val intent = Intent(this, ScanDetailActivity::class.java)
            intent.putExtra("IS_SAFE", false)
            intent.putExtra("CONTENT", "URGENT: Your account has been suspended. Click here to verify: http://bit.ly/fake-bank-login")
            intent.putExtra("REASON", "Suspected Phishing: Contains a shortened link commonly associated with credential harvesting sites. The urgency 'URGENT' is a common social engineering tactic.")
            startActivity(intent)
        }

        // Mock Item 2: Safe
        findViewById<LinearLayout>(R.id.item_sms_2).setOnClickListener {
            val intent = Intent(this, ScanDetailActivity::class.java)
            intent.putExtra("IS_SAFE", true)
            intent.putExtra("CONTENT", "Hey! Are you coming for dinner this weekend? Let me know.")
            intent.putExtra("REASON", "Safe. Sender is in your contacts (Mom) and content contains no malicious patterns.")
            startActivity(intent)
        }
    }
}
