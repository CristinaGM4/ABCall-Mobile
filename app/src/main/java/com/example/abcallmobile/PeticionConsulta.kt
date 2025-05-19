package com.example.abcallmobile

import com.google.gson.annotations.SerializedName

data class PeticionConsulta(
    @SerializedName("tipoDocUsuario")
    val tipoDocUsuario: String,

    @SerializedName("numeroDocUsuario")
    val numeroDocUsuario: String,

    @SerializedName("numeroDocCliente")
    val numeroDocCliente: String,

    @SerializedName("estado")
    val estado: String = "ACTIVO",

    @SerializedName("fechaInicio")
    val fechaInicio: String = "2025/01/01",

    @SerializedName("fechaFin")
    val fechaFin: String = "2025/06/30",

    @SerializedName("pagina")
    val pagina: Int = 1,

    @SerializedName("tamanioPagina")
    val tamanioPagina: Int = 20,

    @SerializedName("descargar")
    val descargar: Boolean = false
)
