package com.example.triptrack_ai

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ThreatDistributionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_threat_distribution)

         findViewById<android.view.View>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }
}
