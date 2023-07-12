package com.example.neocafe.model

data class RegistrationResponse(
    val id: Int,
    val username: String,
    val phone_number: String,
    val verification_code: String,
    val is_verified: Boolean
)
