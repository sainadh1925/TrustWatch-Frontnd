package com.example.triptrack_ai

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.triptrack_ai.adapter.ThreatsAdapter
import com.example.triptrack_ai.model.ThreatItem
import com.example.triptrack_ai.model.ThreatType

class ThreatsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_threats)

        val rvThreats = findViewById<RecyclerView>(R.id.rvThreats)
        rvThreats.layoutManager = LinearLayoutManager(this)

        val items = listOf(
            ThreatItem("PayPal Verification", "Phishing • Email", "2 mins ago", ThreatType.PHISHING, R.drawable.ic_warning), // Ideally use specific icons if available
            ThreatItem("Delivery Reschedule", "Suspicious • SMS", "1 hour ago", ThreatType.SUSPICIOUS, R.drawable.ic_warning), // Placeholder icon
            ThreatItem("Free Crypto Giveaway", "Phishing • URL", "3 hours ago", ThreatType.PHISHING, R.drawable.ic_warning),
            ThreatItem("Login Attempt", "Warning • In-App", "Yesterday", ThreatType.WARNING, R.drawable.ic_warning),
            ThreatItem("Bank Security Alert", "Phishing • Email", "Yesterday", ThreatType.PHISHING, R.drawable.ic_warning),
            ThreatItem("Delivery Confirmation", "Safe • SMS", "2 days ago", ThreatType.SAFE, R.drawable.ic_check_circle)
        )

        val adapter = ThreatsAdapter(items)
        rvThreats.adapter = adapter

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        findViewById<android.view.View>(R.id.nav_btn_home).setOnClickListener {
            val intent = android.content.Intent(this, DashboardActivity::class.java)
            intent.flags = android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP or android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

        findViewById<android.view.View>(R.id.nav_btn_scan).setOnClickListener {
            startActivity(android.content.Intent(this, ScanActivity::class.java))
            overridePendingTransition(0, 0)
        }

        findViewById<android.view.View>(R.id.nav_btn_threats).setOnClickListener {
            // Already here
        }

        findViewById<android.view.View>(R.id.nav_btn_insights).setOnClickListener {
            startActivity(android.content.Intent(this, InsightsActivity::class.java))
            overridePendingTransition(0, 0)
        }

        findViewById<android.view.View>(R.id.nav_btn_profile).setOnClickListener {
            startActivity(android.content.Intent(this, ProfileActivity::class.java))
            overridePendingTransition(0, 0)
        }
    }
}
