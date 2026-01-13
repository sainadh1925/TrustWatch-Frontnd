package com.example.triptrack_ai

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.triptrack_ai.adapter.ThreatsAdapter
import com.example.triptrack_ai.model.ThreatItem
import com.example.triptrack_ai.model.ThreatType

class RecentScansActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recent_scans)

        findViewById<android.view.View>(R.id.btnBack).setOnClickListener {
            finish()
        }

        val rvRecentScans = findViewById<RecyclerView>(R.id.rvRecentScans)
        rvRecentScans.layoutManager = LinearLayoutManager(this)

        // Mock data - reusing ThreatItem for now as it fits "Recent Activity" 
        // In a real app, "Scans" might be different from "Threats", but typically they are related.
        // Or we can create a generic ScanItem. Let's stick to ThreatItem logic for consistency or create a local list.
        val items = listOf(
            ThreatItem("Quick Scan", "System Safe • 100% Secure", "2 mins ago", ThreatType.SAFE, R.drawable.ic_check_circle),
            ThreatItem("Website Scan", "Phishing Detected • URL", "1 hour ago", ThreatType.PHISHING, R.drawable.ic_warning),
            ThreatItem("Full Scan", "System Safe • 100% Secure", "Yesterday", ThreatType.SAFE, R.drawable.ic_check_circle),
            ThreatItem("SMS Scan", "Suspicious Link • SMS", "Yesterday", ThreatType.SUSPICIOUS, R.drawable.ic_warning),
            ThreatItem("Quick Scan", "System Safe • 100% Secure", "2 days ago", ThreatType.SAFE, R.drawable.ic_check_circle),
             ThreatItem("Email Scan", "Phishing Detected • Email", "3 days ago", ThreatType.PHISHING, R.drawable.ic_warning)
        )

        val adapter = ThreatsAdapter(items)
        rvRecentScans.adapter = adapter
    }
}
