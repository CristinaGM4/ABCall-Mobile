package com.example.abcallmobile

data class IncidenteResponse(
    val statusCode: Int,
    val statusDescription: String,
    val data: IncidenteDataWrapper
)

data class IncidenteDataWrapper(
    val data: List<Incidente>
)