package com.example.abcallmobile

import java.io.Serializable

data class Incidente(
    val id: Int,
    val titulo: String,
    val estado: String,
    val fecha: String,
    val empresa: String,
    val cliente: String,
    val descripcion: String,
    val gestion: String
) : Serializable
