package com.example.triptrack_ai

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AiAccuracyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai_accuracy)

         findViewById<android.view.View>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }
}
