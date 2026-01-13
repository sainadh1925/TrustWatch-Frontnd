package com.example.triptrack_ai.model

import com.google.gson.annotations.SerializedName

data class SignupResponse(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String,
    @SerializedName("user_id") val userId: String?
)
