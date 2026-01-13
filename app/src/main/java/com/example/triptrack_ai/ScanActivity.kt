package com.example.triptrack_ai

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class ScanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        // Find clickables
        val cardSms = findViewById<View>(R.id.card_scan_sms)
        val cardEmail = findViewById<View>(R.id.card_scan_email)
        val cardUrl = findViewById<View>(R.id.card_scan_url)
        val navScan = findViewById<View>(R.id.nav_btn_scan)

        // Set listeners
        cardSms.setOnClickListener {
            startActivity(Intent(this, SmsListActivity::class.java))
        }

        cardEmail.setOnClickListener {
            startActivity(Intent(this, EmailListActivity::class.java))
        }

        cardUrl.setOnClickListener {
             startActivity(Intent(this, UrlScanActivity::class.java))
        }

        findViewById<View>(R.id.btn_scan_settings).setOnClickListener {
            startActivity(Intent(this, GeneralSettingsActivity::class.java))
        }
        

        // Bottom Nav Listeners
        findViewById<View>(R.id.nav_btn_scan).setOnClickListener {
             // Already here
        }

        findViewById<View>(R.id.nav_btn_home).setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

        findViewById<View>(R.id.nav_btn_threats).setOnClickListener {
             startActivity(Intent(this, ThreatsActivity::class.java))
             overridePendingTransition(0, 0)
        }

        findViewById<View>(R.id.nav_btn_insights).setOnClickListener {
             startActivity(Intent(this, InsightsActivity::class.java))
             overridePendingTransition(0, 0)
        }

        findViewById<View>(R.id.nav_btn_profile).setOnClickListener {
             startActivity(Intent(this, ProfileActivity::class.java))
             overridePendingTransition(0, 0)
        }
    }
}
