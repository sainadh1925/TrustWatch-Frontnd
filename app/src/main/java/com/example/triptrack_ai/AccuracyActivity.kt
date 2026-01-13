package com.example.triptrack_ai

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class AccuracyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accuracy)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }
}
