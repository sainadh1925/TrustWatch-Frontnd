package com.example.triptrack_ai

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val tvWelcome = findViewById<TextView>(R.id.tv_welcome)
        val ivNotification = findViewById<ImageView>(R.id.iv_notification_bell)

        ivNotification.setOnClickListener {
            startActivity(Intent(this, NotificationsActivity::class.java))
        }

        // Active Protection Listeners
        findViewById<LinearLayout>(R.id.btn_real_time).setOnClickListener {
            startActivity(Intent(this, RealTimeMonitoringActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.btn_ai_detection).setOnClickListener {
            startActivity(Intent(this, AiDetectionActivity::class.java))
        }

        // Recent Activity Header
        // Recent Activity Header
        val tvViewAll = findViewById<TextView>(R.id.tv_view_all_recent)
        tvViewAll?.setOnClickListener {
             startActivity(Intent(this, RecentScansActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.btn_encryption).setOnClickListener {
            startActivity(Intent(this, EncryptedAnalysisActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.btn_accuracy).setOnClickListener {
            startActivity(Intent(this, AccuracyActivity::class.java))
        }

        // Bottom Nav Listeners
        findViewById<TextView>(R.id.nav_btn_scan).setOnClickListener {
             startActivity(Intent(this, ScanActivity::class.java))
        }

        // Profile Nav Listener
        findViewById<TextView>(R.id.nav_btn_profile).setOnClickListener {
             startActivity(Intent(this, ProfileActivity::class.java))
        }

        // Insights Nav Listener
        findViewById<TextView>(R.id.nav_btn_insights).setOnClickListener {
             startActivity(Intent(this, InsightsActivity::class.java))
        }
    }
}
