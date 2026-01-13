package com.example.triptrack_ai.model

data class ThreatItem(
    val title: String,
    val description: String, // e.g., "Phishing • Email"
    val timestamp: String,
    val type:  ThreatType, // PHISHING, SUSPICIOUS, WARNING, SAFE
    val iconResId: Int
)

enum class ThreatType {
    PHISHING, SUSPICIOUS, WARNING, SAFE
}
