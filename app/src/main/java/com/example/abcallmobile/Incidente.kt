package com.example.abcallmobile
import java.io.Serializable

data class Incidente(
    val tipoDocumentoUsuario: String,
    val numDocumentoUsuario: String,
    val numDocumentoCliente: String,
    val descripcion: String,
    val solucionado: Boolean,
    val estado: String,
    val creadoPor: String,
    val fechaCreacion: String
) : Serializable
