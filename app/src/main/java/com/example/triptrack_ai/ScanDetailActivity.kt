package com.example.triptrack_ai

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScanDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_detail)

        val layoutScanning = findViewById<View>(R.id.layout_scanning)
        val layoutResult = findViewById<View>(R.id.layout_result)

        val ivIcon = findViewById<ImageView>(R.id.iv_result_icon)
        val tvTitle = findViewById<TextView>(R.id.tv_result_title)
        val tvPreview = findViewById<TextView>(R.id.tv_content_preview)
        val tvAnalysis = findViewById<TextView>(R.id.tv_analysis_details)
        val btnBack = findViewById<ImageView>(R.id.btnBack)

        btnBack.setOnClickListener { finish() }

        // Get data
        val isSafe = intent.getBooleanExtra("IS_SAFE", true)
        val content = intent.getStringExtra("CONTENT") ?: "N/A"
        val reason = intent.getStringExtra("REASON") ?: "Analysis complete."

        tvPreview.text = content
        tvAnalysis.text = reason

        if (isSafe) {
            tvTitle.text = "Content is Safe"
            tvTitle.setTextColor(Color.parseColor("#4CAF50"))
            ivIcon.setImageResource(R.drawable.ic_check_circle)
            ivIcon.setColorFilter(Color.parseColor("#4CAF50"))
        } else {
            tvTitle.text = "Spam Detected"
            tvTitle.setTextColor(Color.parseColor("#D32F2F"))
            ivIcon.setImageResource(R.drawable.ic_warning)
            ivIcon.setColorFilter(Color.parseColor("#D32F2F"))
        }

        // Simulate Scanning Delay
        Handler(Looper.getMainLooper()).postDelayed({
            layoutScanning.visibility = View.GONE
            layoutResult.visibility = View.VISIBLE
        }, 2000) // 2 second delay
    }
}
