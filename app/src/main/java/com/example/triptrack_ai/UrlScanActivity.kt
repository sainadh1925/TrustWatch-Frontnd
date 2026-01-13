package com.example.triptrack_ai

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UrlScanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_url_scan)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        val etUrl = findViewById<EditText>(R.id.et_url_input)
        val btnScan = findViewById<Button>(R.id.btn_scan_url)

        btnScan.setOnClickListener {
            val url = etUrl.text.toString().trim()
            if (url.isEmpty()) {
                Toast.makeText(this, "Please enter a URL", Toast.LENGTH_SHORT).show()
            } else {
                // Mock Logic: If URL contains "virus" or "evil", it's unsafe. Otherwise safe.
                val isSafe = !(url.contains("virus") || url.contains("evil") || url.contains("attack"))
                val reason = if (isSafe) {
                    "Analysis complete. No malicious patterns found in source code, DNS records, or SSL certificate."
                } else {
                    "Danger! This URL is flagged in our database as a distributor of malware. Do not visit this site."
                }

                val intent = Intent(this, ScanDetailActivity::class.java)
                intent.putExtra("IS_SAFE", isSafe)
                intent.putExtra("CONTENT", url)
                intent.putExtra("REASON", reason)
                startActivity(intent)
            }
        }
    }
}
