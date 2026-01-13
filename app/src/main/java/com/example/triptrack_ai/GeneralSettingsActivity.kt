package com.example.triptrack_ai

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class GeneralSettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_general_settings)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }
}
