package com.example.abcallmobile

data class ClienteResponse(
    val statusCode: Int,
    val statusDescription: String,
    val data: List<Cliente>
)