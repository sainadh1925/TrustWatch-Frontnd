package com.example.triptrack_ai

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NotificationsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        // Back Button
        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        // Mark all read
        findViewById<TextView>(R.id.tvMarkAllRead).setOnClickListener {
             findViewById<android.view.View>(R.id.indicator_1)?.visibility = android.view.View.GONE
             findViewById<android.view.View>(R.id.indicator_2)?.visibility = android.view.View.GONE
             findViewById<android.view.View>(R.id.indicator_3)?.visibility = android.view.View.GONE
             findViewById<android.view.View>(R.id.indicator_4)?.visibility = android.view.View.GONE
             findViewById<android.view.View>(R.id.indicator_5)?.visibility = android.view.View.GONE
             
            Toast.makeText(this, "Marked all as read", Toast.LENGTH_SHORT).show()
        }
    }
}
