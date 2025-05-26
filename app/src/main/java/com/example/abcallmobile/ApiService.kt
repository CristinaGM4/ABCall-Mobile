package com.example.abcallmobile

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {
    @POST("incidentes/v1/crear")
    fun crearIncidente(@Body body: Map<String, String>): Call<CrearIncidenteResponse>

    @POST("incidentes/v1/consultar")
    fun consultarIncidentes(@Body peticion: PeticionConsulta): Call<IncidenteResponse>

    @GET("clientes/v1/listar")
    fun listarClientes(): Call<ClienteResponse>
}


