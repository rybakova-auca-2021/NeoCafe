package com.example.neocafe.api

import com.example.neocafe.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiInterface {

    @POST("/registrations/")
    fun register(@Body request: RegistrationRequest): Call<RegistrationResponse>

    @POST("/verify-phone/")
    fun verify(@Body request: ConfirmRequest): Call<ConfirmResponse>

    @PUT("/birth-date/")
    fun addBirthday(@Body request: BirthdayRequest) : Call<Unit>

    @POST("/login/")
    fun login(@Body request: LoginRequest): Call<Unit>

    @POST("/login-code/")
    fun loginCode(@Body request: ConfirmRequest): Call<Unit>
}