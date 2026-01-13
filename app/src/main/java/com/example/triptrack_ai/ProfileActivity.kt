package com.example.triptrack_ai

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Edit Profile (via Icon)
        findViewById<android.view.View>(R.id.btnEditProfileIcon).setOnClickListener {
             startActivity(Intent(this, EditProfileActivity::class.java))
        }

        // Settings
        findViewById<android.view.View>(R.id.menu_general).setOnClickListener {
             startActivity(Intent(this, GeneralSettingsActivity::class.java))
        }

        // Language
        findViewById<android.view.View>(R.id.menu_language).setOnClickListener {
             startActivity(Intent(this, LanguageActivity::class.java))
        }

        // Sensitivity -> Using Accuracy/Stats? Or mock
        findViewById<android.view.View>(R.id.menu_sensitivity).setOnClickListener {
             // startActivity(Intent(this, AccuracyActivity::class.java)) // Mocking for now
        }
        
        // Privacy -> Mock
         findViewById<android.view.View>(R.id.menu_privacy).setOnClickListener {
             // startActivity(Intent(this, PrivacyActivity::class.java)) 
         }

        // About -> Splash
        findViewById<android.view.View>(R.id.menu_about).setOnClickListener {
             startActivity(Intent(this, SplashActivity::class.java))
        }

        // Logout
        findViewById<android.view.View>(R.id.btnLogout).setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        findViewById<android.view.View>(R.id.nav_btn_home).setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

        findViewById<android.view.View>(R.id.nav_btn_scan).setOnClickListener {
            startActivity(Intent(this, ScanActivity::class.java))
            overridePendingTransition(0, 0)
        }

        findViewById<android.view.View>(R.id.nav_btn_threats).setOnClickListener {
            startActivity(Intent(this, ThreatsActivity::class.java))
            overridePendingTransition(0, 0)
        }

        findViewById<android.view.View>(R.id.nav_btn_insights).setOnClickListener {
            startActivity(Intent(this, InsightsActivity::class.java))
            overridePendingTransition(0, 0)
        }

        findViewById<android.view.View>(R.id.nav_btn_profile).setOnClickListener {
            // Already here
        }
    }
}
