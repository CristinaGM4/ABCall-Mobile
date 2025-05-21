package com.example.abcallmobile

data class CrearIncidenteResponse(
    val statusCode: Int,
    val statusDescription: String,
    val data: IncidenteRaw
)

data class IncidenteRaw(
    val tipoDocumentoUsuario: String,
    val numDocumentoUsuario: String,
    val numDocumentoCliente: String,
    val descripcion: String,
    val status: String,
    val createdBy: String,
    val solved: Boolean
)