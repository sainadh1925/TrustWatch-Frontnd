package com.example.triptrack_ai.api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import okhttp3.ResponseBody

interface ApiService {

    @FormUrlEncoded
    @POST("trustwatch_backend/login.php")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("trustwatch_backend/signup.php")
    fun signup(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("trustwatch_backend/send_otp.php")
    fun sendOtp(
        @Field("email") email: String
    ): Call<String>

    @FormUrlEncoded
    @POST("trustwatch_backend/verify_otp.php")
    fun verifyOtp(
        @Field("email") email: String,
        @Field("otp") otp: String
    ): Call<String>
    
    @FormUrlEncoded
    @POST("trustwatch_backend/reset_password.php")
    fun resetPassword(
        @Field("email") email: String,
        @Field("new_password") newPassword: String
    ): Call<String>
}
