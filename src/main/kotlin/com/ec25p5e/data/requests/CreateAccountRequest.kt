package com.ec25p5e.data.requests

data class CreateAccountRequest(
    val email: String,
    val username: String,
    val password: String
)
