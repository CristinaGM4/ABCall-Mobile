package com.example.abcallmobile
import java.io.Serializable

data class Incidente(
    val id: Int,
    val tipoDocumentoUsuario: Int,
    val numDocumentoUsuario: String,
    val numDocumentoCliente: Long,
    val descripcion: String,
    val estado: String,
    val fechaCreacion: String
) : Serializable