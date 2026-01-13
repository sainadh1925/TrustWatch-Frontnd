package com.example.triptrack_ai

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class InsightsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insights)

        setupBottomNavigation()

        findViewById<android.view.View>(R.id.btnAttackTrends).setOnClickListener {
            startActivity(Intent(this, AttackTrendsActivity::class.java))
        }

        findViewById<android.view.View>(R.id.btnThreatDistribution).setOnClickListener {
            startActivity(Intent(this, ThreatDistributionActivity::class.java))
        }

        findViewById<android.view.View>(R.id.btnLanguageAnalysis).setOnClickListener {
            startActivity(Intent(this, LanguageAnalysisActivity::class.java))
        }

        findViewById<android.view.View>(R.id.btnAiAccuracy).setOnClickListener {
            startActivity(Intent(this, AiAccuracyActivity::class.java))
        }
    }

    private fun setupBottomNavigation() {
        // Since we are using a custom LinearLayout for bottom nav to match DashboardActivity
        findViewById<android.widget.TextView>(R.id.nav_btn_home).setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

        findViewById<android.widget.TextView>(R.id.nav_btn_scan).setOnClickListener {
            startActivity(Intent(this, ScanActivity::class.java))
            overridePendingTransition(0, 0)
        }

        findViewById<android.widget.TextView>(R.id.nav_btn_threats).setOnClickListener {
            startActivity(Intent(this, ThreatsActivity::class.java))
            overridePendingTransition(0, 0)
        }

        findViewById<android.widget.TextView>(R.id.nav_btn_profile).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
            overridePendingTransition(0, 0)
        }
    }
}
